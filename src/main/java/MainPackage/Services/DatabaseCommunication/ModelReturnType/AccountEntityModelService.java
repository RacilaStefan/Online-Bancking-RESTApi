package MainPackage.Services.DatabaseCommunication.ModelReturnType;

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
public class AccountEntityModelService {

    private final AccountRepository repository;
    private final EntityModelGenerator generator;

    public CollectionModel<EntityModel<AccountDto>> findAll() {
        return generator.generateModelFromAccounts(repository.findAll());
    }

    public EntityModel<AccountDto> findById(Long id) {
        return generator.generateModelFromAccount(repository.findById(id).orElseThrow());
    }
}
