
package yous.autolocapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import yous.autolocapi.domain.Vehicule;

@Repository
public interface VehiculeRepository extends CrudRepository<Vehicule, Long> {

}
