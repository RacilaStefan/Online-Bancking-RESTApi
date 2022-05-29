package MainPackage.Services.DatabaseCommunication.EntityModelType;

import MainPackage.Domain.Account;
import MainPackage.Repositories.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service

@AllArgsConstructor
public class AccountDbService {

    private final AccountRepository repository;

    public Set<Account> saveAccounts(Set<Account> accounts) {
        return accounts.stream().map(this::saveAccount).collect(Collectors.toSet());
    }

    public Account saveAccount(Account account) {
        return repository.save(account);
    }
}
