package MainPackage.Services.Utils.Interfaces;

import MainPackage.Dto.AccountDto;
import MainPackage.Dto.AddressDto;
import MainPackage.Dto.CIDto;
import MainPackage.Dto.UserDto;
import MainPackage.GlobalExceptionHandler.CustomExceptions.CustomInvalidInputException;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Set;

public interface IValidator {

    boolean validateUser(UserDto user) throws CustomInvalidInputException, JsonProcessingException;

    boolean validateAddress(AddressDto address) throws CustomInvalidInputException;

    boolean validateAccounts(Set<AccountDto> accounts) throws CustomInvalidInputException;

    boolean validateAccount(AccountDto account) throws CustomInvalidInputException;

    boolean validateCI(CIDto ci) throws CustomInvalidInputException;
}
