package MainPackage.Repositories;

import MainPackage.Domain.CI;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface CIRepository extends CrudRepository<CI, Long> {
}
