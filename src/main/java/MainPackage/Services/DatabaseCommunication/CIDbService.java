package MainPackage.Services.DatabaseCommunication;

import MainPackage.Domain.CI;
import MainPackage.Repositories.CIRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service

@AllArgsConstructor
public class CIDbService {

    private final CIRepository repository;

    //#######  CREATE METHODS  #######//

    public CI saveCI(CI ci) {
        return repository.save(ci);
    }

    //#######  CREATE METHODS  #######//

    //#######  READ METHODS  #######//

    public Iterable<CI> findAll() { return repository.findAll(); }

    public CI findById(Long id) { return repository.findById(id).orElseThrow(); }

    //#######  READ METHODS  #######//

    //#######  DELETE METHODS  #######//

    //#######  DELETE METHODS  #######//
}
