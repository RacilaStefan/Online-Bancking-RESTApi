package MainPackage.Services.Utils.Implementations;

import MainPackage.Domain.Account;
import MainPackage.Domain.User;
import MainPackage.Dto.UserDto;
import MainPackage.GlobalExceptionHandler.CustomExceptions.CustomInvalidInputException;
import MainPackage.Services.DatabaseCommunication.EntityModelType.UserDbService;
import MainPackage.Services.DatabaseCommunication.ModelReturnType.AccountEntityModelService;
import MainPackage.Services.DatabaseCommunication.ModelReturnType.AddressEntityModelService;
import MainPackage.Services.DatabaseCommunication.ModelReturnType.CIEntityModelService;
import MainPackage.Services.DatabaseCommunication.ModelReturnType.UserEntityModelService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Set;

@Service
@AllArgsConstructor
public class RegisterService {

    private final AccountEntityModelService accountDbService;
    private final AddressEntityModelService addressDbService;
    private final CIEntityModelService ciDbService;
    private final UserDbService userDbService;

    private final Validator customValidator;
    private final BankAccountService bankAccountService;
    private final BCryptPasswordEncoder passwordEncoder;

    public User saveUser(UserDto userDto) throws CustomInvalidInputException {
        customValidator.validateUser(userDto);
        customValidator.validateAccounts(userDto.getAccounts());
        customValidator.validateAddress(userDto.getAddress());
        customValidator.validateCI(userDto.getCi());

        User user = userDto.fromDto();
        prepareUserForRegister(user);

        return userDbService.saveUser(user);
    }

    public User saveUser(UserDto userDto, Long id) throws CustomInvalidInputException {
        UserDto user = new UserDto();

        user.getDto(userDbService.findById(id), true);

        try {
            updateUserDto(userDto, user);
        } catch (IllegalAccessException ex) {
            throw new CustomInvalidInputException("");
        }

        return saveUser(user);
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

    private UserDto updateUserDto(UserDto out, UserDto in) throws IllegalAccessException {
        Field[] fields = UserDto.class.getDeclaredFields();
        for (Field field: fields) {
            System.out.println("Value of Field "
                    + field.getName()
                    + " is " + field.get(out));
        }
        //pentru fiecare field din out
        //daca are valoarea null seteaza-l cu echivalentul din in
        return out;
    }
}
