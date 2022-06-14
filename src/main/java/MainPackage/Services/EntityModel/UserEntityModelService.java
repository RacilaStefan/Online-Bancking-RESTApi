package MainPackage.Services.EntityModel;


import MainPackage.Domain.Account;
import MainPackage.Domain.Transaction;
import MainPackage.Domain.User;
import MainPackage.Dto.*;
import MainPackage.GlobalExceptionHandler.CustomExceptions.CustomInvalidInputException;
import MainPackage.Services.DatabaseCommunication.AccountDbService;
import MainPackage.Services.DatabaseCommunication.TransactionDbService;
import MainPackage.Services.DatabaseCommunication.UserDbService;
import MainPackage.Services.Utils.Implementations.EntityModelGenerator;
import MainPackage.Services.Utils.Implementations.Utilities;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service

@AllArgsConstructor
public class UserEntityModelService {

    private final UserDbService userDbService;
    private final AccountDbService accountDbService;
    private final TransactionDbService transactionDbService;

    private final Utilities util;

    private final EntityModelGenerator generator;

    //#######  CREATE METHODS  #######//

    public void saveUser(UserDto userDto) throws CustomInvalidInputException {
        util.validateUserDto(userDto);

        User user = userDto.fromDto();
        util.prepareUserForRegister(user);

        userDbService.saveUser(user);

        //util.sendRegistrationToken(user);
    }

    //#######  CREATE METHODS  #######//

    //#######  READ METHODS  #######//

    public CollectionModel<EntityModel<UserDto>> findAll() {
        return generator.generateModelFromUsers(userDbService.findAll());
    }

    public EntityModel<UserDto> findById(Long id) {
        return generator.generateModelFromUser(userDbService.findById(id));
    }

    public EntityModel<AddressDto> findAddressByUserId(Long id) {
        return generator.generateModelFromAddress(
                userDbService.findById(id).getAddress());
    }

    public EntityModel<CIDto> findCIByUserId(Long id) {
        return generator.generateModelFromCi(
                userDbService.findById(id).getCi());
    }

    public EntityModel<TokenDto> findTokenByUserId(Long id) {
        return generator.generateModelFromToken(
                userDbService.findById(id).getToken());
    }

    public CollectionModel<EntityModel<AccountDto>> findAccountsByUserId(Long id) {
        return generator.generateModelFromAccounts(
                userDbService.findById(id).getAccounts());
    }

    public EntityModel<AccountDto> findAccountByIdByUserId(Long userId, Long accountId) {
        return generator.generateModelFromAccount(
                userDbService.findById(userId).getAccounts()
                        .stream()
                        .filter(account -> account.getId().equals(accountId))
                .findAny().orElseThrow(), true);
    }


    public CollectionModel<EntityModel<TransactionDto>> findTransactionsByUserId(Long id) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        userDbService.findById(id).getAccounts().forEach(account -> {
            transactionDbService.findByFromAccountIBAN(account.getIBAN()).forEach(transactions::add);
        });
        return generator.generateModelFromTransactions(transactions);
    }

    //#######  READ METHODS  #######//

    //#######  UPDATE METHODS  #######//

    public void saveUser(UserDto userDto, Long id) throws CustomInvalidInputException {
        UserDto userInDb = new UserDto();

        userInDb.getDto(userDbService.findById(id), true, true);

        util.updateUserDto(userInDb, userDto);
        util.validateUserDto(userInDb);
        userDbService.saveUser(userInDb.fromDto());
    }

    public void saveAccountByUserId(Long id, AccountDto accountDto) throws CustomInvalidInputException {
        User user = userDbService.findById(id);
        Account account = accountDto.fromDto();
        util.initAccount(account, user);
        user.getAccounts().add(account);

        userDbService.saveUser(user);
    }

    //#######  UPDATE METHODS  #######//

    //#######  DELETE METHODS  #######//

    public void deleteAccountByIdByUserId(Long userId, Long accountId) {
        User user = userDbService.findById(userId);
        Account account = user.getAccounts().stream().filter(acc -> acc.getId().equals(accountId)).findFirst().orElseThrow();

        user.getAccounts().remove(account);
        account.setUser(null);
        accountDbService.deleteAccount(account.getId());
    }

    //#######  DELETE METHODS  #######//
}
