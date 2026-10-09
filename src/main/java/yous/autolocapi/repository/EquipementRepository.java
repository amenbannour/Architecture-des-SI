
package yous.autolocapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import yous.autolocapi.domain.Equipement;

@Repository
public interface EquipementRepository extends CrudRepository<Equipement, Long> {

}
