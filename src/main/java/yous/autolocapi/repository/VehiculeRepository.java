
package yous.autolocapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import yous.autolocapi.domain.Vehicule;

import java.util.List;
import java.math.BigDecimal;

import yous.autolocapi.domain.Vehicule;
import yous.autolocapi.domain.Categorievehicule;
import yous.autolocapi.domain.Statutvehicule;

@Repository
public interface VehiculeRepository extends CrudRepository<Vehicule, Long> {

    List<Vehicule> findByMarque(String marque);

    List<Vehicule> findByCategorie(Categorievehicule categorie);

    List<Vehicule> findByStatut(Statutvehicule statut);

    List<Vehicule> findByMarqueContainingIgnoreCase(String marque);

    List<Vehicule> findByTarifJournalierGreaterThan(
            BigDecimal tarifJournalier);

    List<Vehicule> findByMarqueAndStatut(
            String marque,
            Statutvehicule statut);


}
