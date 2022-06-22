package MainPackage.Services.Utils.Implementations;

import MainPackage.Domain.Account;
import MainPackage.Domain.Token;
import MainPackage.Domain.User;
import MainPackage.Dto.TransactionDto;
import MainPackage.Dto.UserDto;
import MainPackage.EnumsAndStaticClasses.BankDetails;
import MainPackage.EnumsAndStaticClasses.Constants;
import MainPackage.GlobalExceptionHandler.CustomExceptions.CustomInvalidInputException;
import MainPackage.Repositories.TokenRepository;
import MainPackage.Services.DatabaseCommunication.AccountDbService;
import MainPackage.Services.DatabaseCommunication.UserDbService;
import MainPackage.Services.Mail.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class Utilities {

    private final Validator customValidator;
    private final BankAccountService bankAccountService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailService emailService;

    private final TokenRepository tokenRepository;

    private final AccountDbService accountDbService;
    private final UserDbService userDbService;

    public void initAccounts(Set<Account> accounts, User user) {
        for (Account account : accounts) {
            initAccount(account, user);
        }
    }

    public void initAccount(Account account, User user) {
        account.setId(null);
        account.setUser(user);
        account.setBalance(200f);
        account.setIBAN(bankAccountService.generateIBAN());
        account.setCardNumber(bankAccountService.generateCardNumber(account.getCurrency(), account.getType()));
        account.setPin(bankAccountService.generatePIN());
        account.setCVV(bankAccountService.generateCCV());
        account.setExpirationDate(CurrentTime.afterThreeYears);
    }

    public void prepareUserForRegister(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setId(null);
        initAccounts(user.getAccounts(), user);
        user.getAddress().setId(null);
        user.getCi().setId(null);
        user.setToken(new Token());
        user.getToken().setRegistrationToken();
    }

    public UserDto updateUserDto(UserDto userInDb, UserDto updatedUser) throws CustomInvalidInputException {
        Field[] fields = UserDto.class.getDeclaredFields();

        for (Field field: fields) {
            String getMethodName = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
            String setMethodName = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);

            try {
                Method getMethod = UserDto.class.getDeclaredMethod(getMethodName);
                Method setMethod = UserDto.class.getDeclaredMethod(setMethodName, field.getType());

                Object obj = getMethod.invoke(updatedUser);
                //System.out.println("Field " + field.getName() + " of the updated user has the value " + obj);

                if (obj != null && !field.getName().equals("role") && !field.getName().equals("enabled") && !field.getName().equals("locked")) {
                    setMethod.invoke(userInDb, getMethod.invoke(updatedUser));
                    System.out.println("Field " + field.getName() + " has been updated with value " + getMethod.invoke(userInDb));
                }

            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
                throw new CustomInvalidInputException("Server error");
            }
        }

        return userInDb;
    }

    public void validateUserDto(UserDto user) throws CustomInvalidInputException {
        customValidator.validateUser(user);
        customValidator.validateAccounts(user.getAccounts());
        customValidator.validateAddress(user.getAddress());
        customValidator.validateCI(user.getCi());
    }

    public void operateTransaction(TransactionDto transaction) throws CustomInvalidInputException {

        String toIBAN = transaction.getToAccountIBAN();
        String fromIBAN = transaction.getFromAccountIBAN();

        // Check if IBANs are valid so the database isn't queried with bad data
        boolean isValid = customValidator.validateIBAN(toIBAN) &&
                customValidator.validateIBAN(fromIBAN);

        if (!isValid || toIBAN.equals(fromIBAN)) throw new CustomInvalidInputException("IBAN is not valid");

        // Check if specified accounts exist and save them for future validation

        Account to = accountDbService.findByIBAN(toIBAN);
        Account from = accountDbService.findByIBAN(fromIBAN);

        float exchangeRateTo = BankDetails.exchangeRates.get(transaction.getCurrency()) / BankDetails.exchangeRates.get(to.getCurrency());
        float exchangeRateFrom = BankDetails.exchangeRates.get(transaction.getCurrency()) / BankDetails.exchangeRates.get(from.getCurrency());

        // Check if there are sufficient funds
        if (from.getBalance() < transaction.getAmount() * exchangeRateFrom) throw new CustomInvalidInputException("Insufficient Funds");

        // Update balances

        to.setBalance(to.getBalance() + (transaction.getAmount() * exchangeRateTo));
        from.setBalance(from.getBalance() - (transaction.getAmount() * exchangeRateFrom));

        transaction.setDate(LocalDateTime.now());

        accountDbService.saveAccount(to);
        accountDbService.saveAccount(from);
    }

    public void sendRegistrationToken(User user) {
        emailService.sendSimpleMessage(
               user.getEmail(),
                Constants.registerSubject,
                "Verify your account by clicking the link below, or if you cannot click it, paste it in the search bar of your browser. " +
                        "http://localhost:3000/verify?" + user.getToken().getRegistrationToken() +
                        " The link will expire in " +
                        Duration.between(LocalDateTime.now(), user.getToken().getRegistrationTokenExpiration()).toMinutes() +
                        " minutes."
        );
    }

    public void enableUser(String registrationToken) throws CustomInvalidInputException {
        Token token = tokenRepository.findByRegistrationToken(registrationToken).orElseThrow();
        User user = userDbService.findByTokenId(token.getId());

        if (LocalDateTime.now().isAfter(token.getRegistrationTokenExpiration())) {
            token.setRegistrationToken();
            user.setToken(token);
            userDbService.saveUser(user);

            throw new CustomInvalidInputException("The token is expired");
        }

        user.setEnabled(true);
        userDbService.saveUser(user);
    }

    public void sendPasswordChangeToken(User user) {

    }

    public void sendPymentToken(User user) {

    }

    public void send2FACode(User user) throws CustomInvalidInputException {
        Token token = user.getToken();
        if (token.getTwoFACode() == null || LocalDateTime.now().isAfter(token.getTwoFACodeExpiration())) {
            token.setTwoFACode(generate2FACode());
            token.setTwoFACodeExpiration(LocalDateTime.now().plusMinutes(10));
        }

        userDbService.saveUser(user);
        //then send the code on email
    }

    private String generate2FACode() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            builder.append((int) (Math.random() * 9));
        }

        return builder.toString();
    }
}
