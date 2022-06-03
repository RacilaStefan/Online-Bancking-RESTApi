package MainPackage.Services.Utils.Implementations;

import MainPackage.Domain.Account;
import MainPackage.Domain.User;
import MainPackage.Dto.UserDto;
import MainPackage.GlobalExceptionHandler.CustomExceptions.CustomInvalidInputException;
import MainPackage.Services.DatabaseCommunication.UserDbService;
import MainPackage.Services.EntityModel.AccountEntityModelService;
import MainPackage.Services.EntityModel.AddressEntityModelService;
import MainPackage.Services.EntityModel.CIEntityModelService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

@Service
@AllArgsConstructor
public class Utilities {

    private final Validator customValidator;
    private final BankAccountService bankAccountService;
    private final BCryptPasswordEncoder passwordEncoder;

    public Set<Account> getInitAccounts(Set<Account> accounts, User user) {
        for (Account account : accounts) {
            initAccount(account, user);
        }

        return accounts;
    }

    public void initAccount(Account account, User user) {
        account.setId(null);
        account.setUser(user);
        account.setBalance(0L);
        account.setIBAN(bankAccountService.generateIBAN());
        account.setCardNumber(bankAccountService.generateCardNumber(account.getCurrency(), account.getType()));
        account.setPin(bankAccountService.generatePIN());
        account.setCVV(bankAccountService.generateCCV());
        account.setExpirationDate(CurrentTime.afterThreeYears);
    }

    public void prepareUserForRegister(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setId(null);
        user.setAccounts(getInitAccounts(user.getAccounts(), user));
        user.getAddress().setId(null);
        user.getCi().setId(null);
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

                if (obj != null && !field.getName().equals("role")) {
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
}
