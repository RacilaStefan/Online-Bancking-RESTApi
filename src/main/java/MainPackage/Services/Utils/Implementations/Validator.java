package MainPackage.Services.Utils.Implementations;

import MainPackage.Dto.AccountDto;
import MainPackage.Dto.AddressDto;
import MainPackage.Dto.CIDto;
import MainPackage.Dto.UserDto;
import MainPackage.EnumsAndStaticClasses.BankDetails;
import MainPackage.GlobalExceptionHandler.CustomExceptions.CustomInvalidInputException;
import MainPackage.Services.Utils.Interfaces.IValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.math.BigInteger;
import java.util.Set;

@Service

@AllArgsConstructor
public class Validator implements IValidator {

    private final javax.validation.Validator validator;
    private final BankAccountService bankAccountService;

    @Override
    public void validateUser(UserDto user) throws CustomInvalidInputException {
        Set<ConstraintViolation<UserDto>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            throw new CustomInvalidInputException(violations.toString());
        }
    }

    @Override
    public void validateAddress(AddressDto address) throws CustomInvalidInputException {
        Set<ConstraintViolation<AddressDto>> violations = validator.validate(address);
        if (!violations.isEmpty()) {
            throw new CustomInvalidInputException(violations.toString());
        }
    }

    @Override
    public void validateAccounts(Set<AccountDto> accounts) throws CustomInvalidInputException {
        for (AccountDto account : accounts) {
            validateAccount(account);
        }
    }

    @Override
    public void validateAccount(AccountDto account) throws CustomInvalidInputException {
        Set<ConstraintViolation<AccountDto>> violations = validator.validate(account);
        if (!violations.isEmpty()) {
            throw new CustomInvalidInputException(violations.toString());
        }
    }

    @Override
    public void validateCI(CIDto ci) throws CustomInvalidInputException {
        Set<ConstraintViolation<CIDto>> violations = validator.validate(ci);
        if (!violations.isEmpty()) {
            throw new CustomInvalidInputException(violations.toString());
        }
    }

    public Boolean validateIBAN(String IBAN) {

        if (IBAN.length() != BankDetails.IBAN_Length) return false;

        String temp = IBAN.substring(0, 4);
        IBAN = IBAN.substring(4);
        IBAN += temp;
        String intIBAN = bankAccountService.getIntegerRepresentationFromIBAN(IBAN);

        int remainder = new BigInteger(intIBAN).mod(BigInteger.valueOf(97)).intValue();

        return remainder == 1;
    }
}
