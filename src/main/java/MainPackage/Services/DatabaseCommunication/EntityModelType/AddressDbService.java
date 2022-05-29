package MainPackage.Services.DatabaseCommunication.EntityModelType;

import MainPackage.Domain.Address;
import MainPackage.Repositories.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service

@AllArgsConstructor
public class AddressDbService {

    private final AddressRepository repository;

    public Address saveAddress(Address address) {
        return repository.save(address);
    }
}
