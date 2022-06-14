package MainPackage.Services.DatabaseCommunication;


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

    //#######  CREATE METHODS  #######//

    public void saveUser(User user) throws CustomInvalidInputException {
        try {
            repository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new CustomInvalidInputException(ex.getCause().getCause().getMessage());
        }
    }

    //#######  CREATE METHODS  #######//

    //#######  READ METHODS  #######//

    public Iterable<User> findAll() { return repository.findAll(); }

    public User findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username).orElseThrow();
    }

    public User findByTokenId(Long id) { return repository.findByTokenId(id).orElseThrow(); }

    //#######  READ METHODS  #######//

    //#######  DELETE METHODS  #######//

    //#######  DELETE METHODS  #######//
}
