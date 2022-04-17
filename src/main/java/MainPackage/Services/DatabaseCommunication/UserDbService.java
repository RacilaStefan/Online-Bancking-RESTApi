package MainPackage.Services.DatabaseCommunication;

import MainPackage.Domain.User;
import MainPackage.Dto.*;
import MainPackage.GlobalExceptionHandler.CustomExceptions.CustomInvalidInputException;
import MainPackage.Repositories.UserRepository;
import MainPackage.Services.Utils.Implementations.EntityModelGenerator;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.io.IOException;

@Service

@AllArgsConstructor
public class UserDbService {

    private final UserRepository repository;
    private final EntityModelGenerator generator;

    public CollectionModel<EntityModel<UserDto>> findAll() {
        return generator.generateModelFromUsers(repository.findAll());
    }

    public EntityModel<UserDto> findById(Long id) {
        return generator.generateModelFromUser(repository.findById(id).orElseThrow());
    }

    public EntityModel<AddressDto> findAddressByUserId(Long id) {
        return generator.generateModelFromAddress(
                repository.findById(id).orElseThrow().getAddress());
    }

    public EntityModel<CIDto> findCIByUserId(Long id) {
        return generator.generateModelFromCi(
                repository.findById(id).orElseThrow().getCi());
    }

    public EntityModel<TokenDto> findTokenByUserId(Long id) {
        return generator.generateModelFromToken(
                repository.findById(id).orElseThrow().getToken());
    }

    public CollectionModel<EntityModel<AccountDto>> findAccountsByUserId(Long id) {
        return generator.generateModelFromAccounts(
                repository.findById(id).orElseThrow().getAccounts());
    }

    public EntityModel<AccountDto> findAccountByIdByUserId(Long userId, Long accountId) {
        return generator.generateModelFromAccount(
                repository.findById(userId).orElseThrow().getAccounts()
                        .stream()
                        .filter(account -> account.getId().equals(accountId))
                .findAny().orElseThrow());
    }

    public User saveUser(User user) throws CustomInvalidInputException {
        try {
            repository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new CustomInvalidInputException(ex.getCause().getCause().getMessage());
        }
        return user;
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
