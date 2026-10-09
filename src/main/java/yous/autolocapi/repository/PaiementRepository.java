
package yous.autolocapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import yous.autolocapi.domain.Paiement;

@Repository
public interface PaiementRepository extends CrudRepository<Paiement, Long> {

}
