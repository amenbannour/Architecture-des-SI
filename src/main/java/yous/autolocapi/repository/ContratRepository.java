
package yous.autolocapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import yous.autolocapi.domain.Contrat;

@Repository
public interface ContratRepository extends CrudRepository<Contrat, Long> {

}
