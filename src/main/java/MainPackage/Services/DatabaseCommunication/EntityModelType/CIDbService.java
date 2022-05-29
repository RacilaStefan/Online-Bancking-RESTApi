package MainPackage.Services.DatabaseCommunication.EntityModelType;

import MainPackage.Domain.CI;
import MainPackage.Repositories.CIRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service

@AllArgsConstructor
public class CIDbService {

    private final CIRepository repository;

    public CI saveCI(CI ci) {
        return repository.save(ci);
    }
}
