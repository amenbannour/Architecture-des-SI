package yous.autolocapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import yous.autolocapi.domain.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository

public interface ClientRepository extends CrudRepository <Client,Long>{

    @Query("""
       SELECT c.idClient, c.nom, COUNT(r)
       FROM Client c
       LEFT JOIN c.reservations r
       GROUP BY c.idClient, c.nom
       """)
    List<Object[]> countReservationsByClient();




}
