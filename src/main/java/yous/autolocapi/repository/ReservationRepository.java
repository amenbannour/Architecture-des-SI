
package yous.autolocapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import yous.autolocapi.domain.Reservation;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

}
