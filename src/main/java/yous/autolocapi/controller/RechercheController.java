
package yous.autolocapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import yous.autolocapi.domain.*;
import yous.autolocapi.service.RechercheService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/recherche")
@RequiredArgsConstructor
@Tag(name = "Atelier Keywords",
        description = "Recherches avec Spring Data JPA")
public class RechercheController {

    private final RechercheService rechercheService;

    // QUESTION 1
    @Operation(summary = "Q1 - Rechercher par marque")
    @GetMapping("/vehicules/marque")
    public List<Vehicule> question1(
            @RequestParam String marque) {

        return rechercheService.rechercherParMarque(marque);
    }

    // QUESTION 2
    @Operation(summary = "Q2 - Rechercher par categorie")
    @GetMapping("/vehicules/categorie")
    public List<Vehicule> question2(
            @RequestParam Categorievehicule categorie) {

        return rechercheService.rechercherParCategorie(categorie);
    }

    // QUESTION 3
    @Operation(summary = "Q3 - Vehicules disponibles")
    @GetMapping("/vehicules/disponibles")
    public List<Vehicule> question3() {

        return rechercheService.rechercherDisponibles();
    }

    // QUESTION 4
    @Operation(summary = "Q4 - Rechercher agences par ville")
    @GetMapping("/agences/ville")
    public List<Agence> question4(
            @RequestParam String ville) {

        return rechercheService.rechercherAgencesParVille(ville);
    }

    // QUESTION 5
    @Operation(summary = "Q5 - Marque contenant un texte")
    @GetMapping("/vehicules/marque-contient")
    public List<Vehicule> question5(
            @RequestParam String texte) {

        return rechercheService.rechercherMarqueContenant(texte);
    }

    // QUESTION 8
    @Operation(summary = "Q8 - Tarif superieur a 100 DT")
    @GetMapping("/vehicules/tarif-superieur-100")
    public List<Vehicule> question8() {

        return rechercheService.rechercherTarifSuperieur100();
    }

    // QUESTION 11
    @Operation(summary = "Q11 - Marque ET disponible")
    @GetMapping("/vehicules/marque-disponible")
    public List<Vehicule> question11(
            @RequestParam String marque) {

        return rechercheService.rechercherMarqueDisponible(marque);
    }

    // QUESTION 14
    @Operation(summary = "Q14 - Reservations apres une date")
    @GetMapping("/reservations/apres-date")
    public List<Reservation> question14(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date) {

        return rechercheService.rechercherReservationsApres(date);
    }
}
