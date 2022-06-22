package MainPackage.Services.DatabaseCommunication;

import MainPackage.Domain.Transaction;
import MainPackage.Repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

@AllArgsConstructor
public class TransactionDbService {

    private final TransactionRepository repository;

    //#######  CREATE METHODS  #######//

    public Transaction saveTransaction(Transaction transaction) {
        return repository.save(transaction);
    }

    //#######  CREATE METHODS  #######//

    //#######  READ METHODS  #######//

    public Iterable<Transaction> findAll() { return repository.findAll(); }

    public Transaction findById(Long id) { return repository.findById(id).orElseThrow(); }

    public Iterable<Transaction> findByFromAccountIBAN(String IBAN) { return repository.findByFromAccountIBAN(IBAN); }

    public List<Transaction> findByIBAN(String IBAN) {
        List<Transaction> transactions = new ArrayList<>();
        repository.findByFromAccountIBAN(IBAN).forEach(transactions::add);
        repository.findByToAccountIBAN(IBAN).forEach(transactions::add);

        return transactions;
    }

    //#######  READ METHODS  #######//

    //#######  DELETE METHODS  #######//

    //#######  DELETE METHODS  #######//
}
