
package yous.autolocapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import yous.autolocapi.domain.*;
import yous.autolocapi.repository.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RechercheService {

    private final VehiculeRepository vehiculeRepository;
    private final AgenceRepository agenceRepository;
    private final ReservationRepository reservationRepository;

    // Q1 : Vehicules d'une marque precise
    public List<Vehicule> rechercherParMarque(String marque) {
        return vehiculeRepository.findByMarque(marque);
    }

    // Q2 : Vehicules d'une categorie donnee
    public List<Vehicule> rechercherParCategorie(
            Categorievehicule categorie) {

        return vehiculeRepository.findByCategorie(categorie);
    }

    // Q3 : Vehicules disponibles
    public List<Vehicule> rechercherDisponibles() {
        return vehiculeRepository.findByStatut(
                Statutvehicule.DISPONIBLE);
    }

    // Q4 : Agences situees dans une ville
    public List<Agence> rechercherAgencesParVille(String ville) {
        return agenceRepository.findByVille(ville);
    }

    // Q5 : Marque contenant une chaine
    public List<Vehicule> rechercherMarqueContenant(String texte) {
        return vehiculeRepository
                .findByMarqueContainingIgnoreCase(texte);
    }

    // Q8 : Tarif journalier superieur a 100 DT
    public List<Vehicule> rechercherTarifSuperieur100() {
        return vehiculeRepository
                .findByTarifJournalierGreaterThan(
                        new BigDecimal("100"));
    }

    // Q11 : Marque precise ET statut DISPONIBLE
    public List<Vehicule> rechercherMarqueDisponible(String marque) {
        return vehiculeRepository.findByMarqueAndStatut(
                marque, Statutvehicule.DISPONIBLE);
    }

    // Q14 : Reservations apres une date
    public List<Reservation> rechercherReservationsApres(
            LocalDate date) {

        return reservationRepository.findByDateDebutAfter(date);
    }
}
