package MainPackage.Services.DatabaseCommunication;

import MainPackage.Domain.Address;
import MainPackage.Repositories.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service

@AllArgsConstructor
public class AddressDbService {

    private final AddressRepository repository;

    //#######  CREATE METHODS  #######//

    public Address saveAddress(Address address) {
        return repository.save(address);
    }

    //#######  CREATE METHODS  #######//

    //#######  READ METHODS  #######//

    public Iterable<Address> findAll() { return repository.findAll(); }

    public Address findById(Long id) { return repository.findById(id).orElseThrow(); }

    //#######  READ METHODS  #######//

    //#######  DELETE METHODS  #######//

    //#######  DELETE METHODS  #######//



}
