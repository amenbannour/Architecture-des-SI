
package yous.autolocapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import yous.autolocapi.domain.Agence;
import yous.autolocapi.domain.Equipement;
import yous.autolocapi.domain.Vehicule;

import yous.autolocapi.repository.AgenceRepository;
import yous.autolocapi.repository.EquipementRepository;
import yous.autolocapi.repository.VehiculeRepository;

import java.util.HashSet;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class VehiculeServiceImpl implements IVehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private final AgenceRepository agenceRepository;
    private final EquipementRepository equipementRepository;

    // CRUD : Recuperer tous les vehicules
    @Override
    public List<Vehicule> retrieveAllVehicules() {
        return StreamSupport.stream(
                vehiculeRepository.findAll().spliterator(),
                false
        ).toList();
    }

    // CRUD : Ajouter un vehicule
    @Override
    public Vehicule addVehicule(Vehicule v) {
        return vehiculeRepository.save(v);
    }

    // CRUD : Modifier un vehicule
    @Override
    public Vehicule updateVehicule(Vehicule v) {
        return vehiculeRepository.save(v);
    }

    // CRUD : Rechercher un vehicule
    @Override
    public Vehicule retrieveVehicule(Long idVehicule) {
        return vehiculeRepository.findById(idVehicule)
                .orElse(null);
    }

    // CRUD : Supprimer un vehicule
    @Override
    public void removeVehicule(Long idVehicule) {
        vehiculeRepository.deleteById(idVehicule);
    }

    // CRUD : Ajouter plusieurs vehicules
    @Override
    public List<Vehicule> addVehicules(List<Vehicule> vehicules) {
        return StreamSupport.stream(
                vehiculeRepository.saveAll(vehicules).spliterator(),
                false
        ).toList();
    }

    // SCENARIO 2 : Affecter un vehicule a une agence
    @Override
    @Transactional
    public Vehicule affecterVehicule(
            Long idVehicule,
            Long idAgence) {

        Vehicule vehicule = vehiculeRepository.findById(idVehicule)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Vehicule introuvable : " + idVehicule
                        )
                );

        Agence agence = agenceRepository.findById(idAgence)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Agence introuvable : " + idAgence
                        )
                );

        // Affecter le vehicule a l'agence
        vehicule.setAgence(agence);

        // Enregistrer la modification
        return vehiculeRepository.save(vehicule);
    }

    // SCENARIO 3 : Affecter un equipement a un vehicule
    @Override
    @Transactional
    public Vehicule affecterEquipement(
            Long idVehicule,
            Long idEquipement) {

        Vehicule vehicule = vehiculeRepository.findById(idVehicule)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Vehicule introuvable : " + idVehicule
                        )
                );

        Equipement equipement =
                equipementRepository.findById(idEquipement)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Equipement introuvable : " + idEquipement
                                )
                        );

        // Initialiser la collection si necessaire
        if (vehicule.getEquipements() == null) {
            vehicule.setEquipements(new HashSet<>());
        }

        // Ajouter l'equipement au vehicule
        vehicule.getEquipements().add(equipement);

        // Enregistrer la relation
        return vehiculeRepository.save(vehicule);
    }

    // SCENARIO 3 : Desaffecter un equipement d'un vehicule
    @Override
    @Transactional
    public Vehicule desaffecterEquipement(
            Long idVehicule,
            Long idEquipement) {

        Vehicule vehicule = vehiculeRepository.findById(idVehicule)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Vehicule introuvable : " + idVehicule
                        )
                );

        Equipement equipement =
                equipementRepository.findById(idEquipement)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Equipement introuvable : " + idEquipement
                                )
                        );

        // Retirer l'equipement du vehicule
        if (vehicule.getEquipements() != null) {
            vehicule.getEquipements().remove(equipement);
        }

        // Enregistrer la modification
        return vehiculeRepository.save(vehicule);
    }
}
