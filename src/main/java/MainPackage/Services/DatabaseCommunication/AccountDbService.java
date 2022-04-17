package MainPackage.Services.DatabaseCommunication;

import MainPackage.Domain.Account;
import MainPackage.Dto.AccountDto;
import MainPackage.Repositories.AccountRepository;
import MainPackage.Services.Utils.Implementations.EntityModelGenerator;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service

@AllArgsConstructor
public class AccountDbService {

    private final AccountRepository repository;
    private final EntityModelGenerator generator;

    public CollectionModel<EntityModel<AccountDto>> findAll() {
        return generator.generateModelFromAccounts(repository.findAll());
    }

    public EntityModel<AccountDto> findById(Long id) {
        return generator.generateModelFromAccount(repository.findById(id).orElseThrow());
    }

    public Set<Account> saveAccounts(Set<Account> accounts) {
        return accounts.stream().map(this::saveAccount).collect(Collectors.toSet());
    }
    public Account saveAccount(Account account) {
        return repository.save(account);
    }
}
