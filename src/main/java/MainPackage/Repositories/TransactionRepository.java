package MainPackage.Repositories;

import MainPackage.Domain.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    Iterable<Transaction> findByFromAccountIBAN(String IBAN);

    Iterable<Transaction> findByToAccountIBAN(String IBAN);
}
