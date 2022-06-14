package MainPackage.Services.DatabaseCommunication;

import MainPackage.Domain.Transaction;
import MainPackage.Repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

    //#######  READ METHODS  #######//

    //#######  DELETE METHODS  #######//

    //#######  DELETE METHODS  #######//
}
