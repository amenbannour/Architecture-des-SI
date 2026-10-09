
package yous.autolocapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import yous.autolocapi.domain.Maintenance;

@Repository
public interface MaintenanceRepository extends CrudRepository<Maintenance, Long> {

}
