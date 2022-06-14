package MainPackage.Services.EntityModel;

import MainPackage.Domain.Transaction;
import MainPackage.Dto.TransactionDto;
import MainPackage.GlobalExceptionHandler.CustomExceptions.CustomInvalidInputException;
import MainPackage.Services.DatabaseCommunication.TransactionDbService;
import MainPackage.Services.Utils.Implementations.EntityModelGenerator;
import MainPackage.Services.Utils.Implementations.Utilities;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service

@AllArgsConstructor
public class TransactionEntityModelService {

    private final EntityModelGenerator generator;
    private final TransactionDbService service;
    private final Utilities util;

    //#######  CREATE METHODS  #######//

    public void saveTransaction(TransactionDto transactionDto) throws CustomInvalidInputException {
        util.operateTransaction(transactionDto);

        Transaction transaction = transactionDto.fromDto();
        transaction.setId(null);

        service.saveTransaction(transaction);
    }

    //#######  CREATE METHODS  #######//

    //#######  READ METHODS  #######//

    public CollectionModel<EntityModel<TransactionDto>> findAll() {
        return generator.generateModelFromTransactions(service.findAll());
    }

    public EntityModel<TransactionDto> findById(Long id) {
        return generator.generateModelFromTransaction(service.findById(id));
    }

    //#######  READ METHODS  #######//

    //#######  UPDATE METHODS  #######//

    //#######  UPDATE METHODS  #######//

    //#######  DELETE METHODS  #######//

    //#######  DELETE METHODS  #######//
}
