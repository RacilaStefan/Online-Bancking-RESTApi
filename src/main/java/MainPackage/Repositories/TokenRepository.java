package MainPackage.Repositories;

import MainPackage.Domain.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TokenRepository extends CrudRepository<Token, Long> {

    Optional<Token> findByRegistrationToken(String registrationToken);
}
