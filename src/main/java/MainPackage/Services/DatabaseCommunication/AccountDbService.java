package MainPackage.Services.DatabaseCommunication;

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

    //#######  CREATE METHODS  #######//

    public Account saveAccount(Account account) {
        return repository.save(account);
    }

    public Set<Account> saveAccounts(Set<Account> accounts) {
        return accounts.stream().map(this::saveAccount).collect(Collectors.toSet());
    }

    //#######  CREATE METHODS  #######//

    //#######  READ METHODS  #######//

    public Iterable<Account> findAll() { return repository.findAll(); }

    public Account findById(Long id) { return repository.findById(id).orElseThrow(); }

    public Account findByIBAN(String IBAN) {
        return repository.findByIBAN(IBAN).orElseThrow();
    }

    //#######  READ METHODS  #######//

    //#######  DELETE METHODS  #######//

    public void deleteAccount(Long id) {
        repository.deleteById(id);
    }

    //#######  DELETE METHODS  #######//
}
