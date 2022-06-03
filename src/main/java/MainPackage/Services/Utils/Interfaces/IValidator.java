package MainPackage.Services.Utils.Interfaces;

import MainPackage.Dto.AccountDto;
import MainPackage.Dto.AddressDto;
import MainPackage.Dto.CIDto;
import MainPackage.Dto.UserDto;
import MainPackage.GlobalExceptionHandler.CustomExceptions.CustomInvalidInputException;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Set;

public interface IValidator {

    void validateUser(UserDto user) throws CustomInvalidInputException;

    void validateAddress(AddressDto address) throws CustomInvalidInputException;

    void validateAccounts(Set<AccountDto> accounts) throws CustomInvalidInputException;

    void validateAccount(AccountDto account) throws CustomInvalidInputException;

    void validateCI(CIDto ci) throws CustomInvalidInputException;
}
