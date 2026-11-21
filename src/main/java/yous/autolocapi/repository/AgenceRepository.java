package yous.autolocapi.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import yous.autolocapi.domain.Agence;
import java.util.List;
import yous.autolocapi.domain.Agence;
@Repository
public interface AgenceRepository extends CrudRepository<Agence, Long> {

    List<Agence> findByVille(String ville);

    @Query("""
       SELECT a.idagence, a.nom, COUNT(v)
       FROM Agence a
       LEFT JOIN a.vehicules v
       GROUP BY a.idagence, a.nom
       """)
    List<Object[]> countVehiculesByAgence();


}