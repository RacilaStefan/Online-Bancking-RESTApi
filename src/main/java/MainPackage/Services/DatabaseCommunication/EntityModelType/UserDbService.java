package MainPackage.Services.DatabaseCommunication.EntityModelType;


import MainPackage.Domain.User;
import MainPackage.GlobalExceptionHandler.CustomExceptions.CustomInvalidInputException;
import MainPackage.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service

@AllArgsConstructor
public class UserDbService {

    private final UserRepository repository;

    public User findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public User saveUser(User user) throws CustomInvalidInputException {
        try {
            repository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new CustomInvalidInputException(ex.getCause().getCause().getMessage());
        }
        return user;
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
