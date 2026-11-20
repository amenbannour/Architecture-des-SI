
package yous.autolocapi.service;

import yous.autolocapi.domain.Vehicule;
import java.util.List;

public interface IVehiculeService {

    // CRUD existant
    List<Vehicule> retrieveAllVehicules();

    Vehicule addVehicule(Vehicule v);

    Vehicule updateVehicule(Vehicule v);

    Vehicule retrieveVehicule(Long idVehicule);

    void removeVehicule(Long idVehicule);

    List<Vehicule> addVehicules(List<Vehicule> vehicules);

    // SCENARIO 2 : Affecter un vehicule a une agence
    Vehicule affecterVehicule(Long idVehicule, Long idAgence);

    // SCENARIO 3 : Ajouter un equipement a un vehicule
    Vehicule affecterEquipement(Long idVehicule, Long idEquipement);

    // SCENARIO 3 : Retirer un equipement d'un vehicule
    Vehicule desaffecterEquipement(Long idVehicule, Long idEquipement);
}
