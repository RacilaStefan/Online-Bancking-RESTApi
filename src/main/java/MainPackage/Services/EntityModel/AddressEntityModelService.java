package MainPackage.Services.EntityModel;

import MainPackage.Dto.AddressDto;
import MainPackage.Repositories.AddressRepository;
import MainPackage.Services.DatabaseCommunication.AddressDbService;
import MainPackage.Services.Utils.Implementations.EntityModelGenerator;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service

@AllArgsConstructor
public class AddressEntityModelService {

    private final AddressDbService service;
    private final EntityModelGenerator generator;

    //#######  CREATE METHODS  #######//

    //#######  CREATE METHODS  #######//

    //#######  READ METHODS  #######//

    public CollectionModel<EntityModel<AddressDto>> findAll() {
        return generator.generateModelFromAddresses(service.findAll());
    }

    public EntityModel<AddressDto> findById(Long id) {
        return generator.generateModelFromAddress(service.findById(id));
    }

    //#######  READ METHODS  #######//

    //#######  UPDATE METHODS  #######//

    //#######  UPDATE METHODS  #######//

    //#######  DELETE METHODS  #######//

    //#######  DELETE METHODS  #######//
}
