package MainPackage.Services.DatabaseCommunication;

import MainPackage.Domain.Account;
import MainPackage.Domain.User;
import MainPackage.Dto.UserDto;
import MainPackage.GlobalExceptionHandler.CustomExceptions.CustomInvalidInputException;
import MainPackage.Services.Utils.Implementations.BankAccountService;
import MainPackage.Services.Utils.Implementations.CurrentTime;
import MainPackage.Services.Utils.Implementations.Validator;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class RegisterService {

    private final AccountDbService accountDbService;
    private final AddressDbService addressDbService;
    private final CIDbService ciDbService;
    private final UserDbService userDbService;

    private final Validator customValidator;
    private final BankAccountService bankAccountService;
    private final BCryptPasswordEncoder passwordEncoder;

    public User registerUser(UserDto userDto) throws CustomInvalidInputException {
        customValidator.validateUser(userDto);
        customValidator.validateAccounts(userDto.getAccounts());
        customValidator.validateAddress(userDto.getAddress());
        customValidator.validateCI(userDto.getCi());

        User user = userDto.fromDto();
        prepareUserForRegister(user);

        return userDbService.saveUser(user);
    }

    private Set<Account> getInitAccounts(Set<Account> accounts, User user) {
        for (Account account : accounts) {
            initAccount(account, user);
        }

        return accounts;
    }

    private void initAccount(Account account, User user) {
        account.setId(null);
        account.setUser(user);
        account.setBalance(0L);
        account.setIBAN(bankAccountService.generateIBAN());
        account.setCardNumber(bankAccountService.generateCardNumber(account.getCurrency(), account.getType()));
        account.setPin(bankAccountService.generatePIN());
        account.setCVV(bankAccountService.generateCCV());
        account.setExpirationDate(CurrentTime.afterThreeYears);
    }

    private void prepareUserForRegister(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setId(null);
        user.setAccounts(getInitAccounts(user.getAccounts(), user));
        user.getAddress().setId(null);
        user.getCi().setId(null);
    }
}
