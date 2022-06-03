package MainPackage.Services.EntityModel;

import MainPackage.Dto.AccountDto;
import MainPackage.Repositories.AccountRepository;
import MainPackage.Services.DatabaseCommunication.AccountDbService;
import MainPackage.Services.Utils.Implementations.EntityModelGenerator;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service

@AllArgsConstructor
public class AccountEntityModelService {

    private final AccountDbService service;
    private final EntityModelGenerator generator;

    //#######  CREATE METHODS  #######//

    //#######  CREATE METHODS  #######//

    //#######  READ METHODS  #######//

    public CollectionModel<EntityModel<AccountDto>> findAll() {
        return generator.generateModelFromAccounts(service.findAll());
    }

    public EntityModel<AccountDto> findById(Long id) {
        return generator.generateModelFromAccount(service.findById(id), false);
    }

    //#######  READ METHODS  #######//

    //#######  UPDATE METHODS  #######//

    //#######  UPDATE METHODS  #######//

    //#######  DELETE METHODS  #######//

    //#######  DELETE METHODS  #######//
}
