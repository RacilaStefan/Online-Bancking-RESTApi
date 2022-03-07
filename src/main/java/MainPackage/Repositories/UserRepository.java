package MainPackage.Repositories;

import MainPackage.Domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
