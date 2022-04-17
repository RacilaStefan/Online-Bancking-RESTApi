package MainPackage.Services.DatabaseCommunication;

import MainPackage.Domain.Address;
import MainPackage.Dto.AddressDto;
import MainPackage.Repositories.AddressRepository;
import MainPackage.Services.Utils.Implementations.EntityModelGenerator;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service

@AllArgsConstructor
public class AddressDbService {

    private final AddressRepository repository;
    private final EntityModelGenerator generator;

    public CollectionModel<EntityModel<AddressDto>> findAll() {
        return generator.generateModelFromAddresses(repository.findAll());
    }

    public EntityModel<AddressDto> findById(Long id) {
        return generator.generateModelFromAddress(
                repository.findById(id).orElseThrow());
    }

    public Address saveAddress(Address address) {
        return repository.save(address);
    }
}
