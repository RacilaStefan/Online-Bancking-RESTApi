package MainPackage.Services.DatabaseCommunication;

import MainPackage.Domain.CI;
import MainPackage.Dto.CIDto;
import MainPackage.Repositories.CIRepository;
import MainPackage.Services.Utils.Implementations.EntityModelGenerator;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service

@AllArgsConstructor
public class CIDbService {

    private final CIRepository repository;
    private final EntityModelGenerator generator;

    public CollectionModel<EntityModel<CIDto>> findAll() {
        return generator.generateModelFromCIs(repository.findAll());
    }

    public EntityModel<CIDto> findById(Long id) {
        return generator.generateModelFromCi(repository.findById(id).orElseThrow());
    }

    public CI saveCI(CI ci) {
        return repository.save(ci);
    }
}
