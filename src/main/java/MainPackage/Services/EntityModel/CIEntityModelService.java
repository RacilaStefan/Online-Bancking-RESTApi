package MainPackage.Services.EntityModel;

import MainPackage.Dto.CIDto;
import MainPackage.Repositories.CIRepository;
import MainPackage.Services.DatabaseCommunication.CIDbService;
import MainPackage.Services.Utils.Implementations.EntityModelGenerator;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service

@AllArgsConstructor
public class CIEntityModelService {

    private final CIDbService service;
    private final EntityModelGenerator generator;

    //#######  CREATE METHODS  #######//

    //#######  CREATE METHODS  #######//

    //#######  READ METHODS  #######//

    public CollectionModel<EntityModel<CIDto>> findAll() {
        return generator.generateModelFromCIs(service.findAll());
    }

    public EntityModel<CIDto> findById(Long id) {
        return generator.generateModelFromCi(service.findById(id));
    }

    //#######  READ METHODS  #######//

    //#######  UPDATE METHODS  #######//

    //#######  UPDATE METHODS  #######//

    //#######  DELETE METHODS  #######//

    //#######  DELETE METHODS  #######//
}
