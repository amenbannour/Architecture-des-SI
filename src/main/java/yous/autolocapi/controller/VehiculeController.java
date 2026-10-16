
package yous.autolocapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import yous.autolocapi.domain.Vehicule;
import yous.autolocapi.service.IVehiculeService;

import java.util.List;

@RestController
@RequestMapping("/vehicules")
@RequiredArgsConstructor
@Tag(name = "Vehicules", description = "Gestion des vehicules")
public class VehiculeController {

    private final IVehiculeService vehiculeService;

    @Operation(summary = "Ajouter un vehicule")
    @ApiResponse(responseCode = "200", description = "Vehicule ajoute")
    @PostMapping
    public ResponseEntity<Vehicule> addVehicule(
            @RequestBody Vehicule v) {

        v.setIdVehicule(null);
        return ResponseEntity.ok(vehiculeService.addVehicule(v));
    }

    @Operation(summary = "Afficher tous les vehicules")
    @ApiResponse(responseCode = "200", description = "Liste des vehicules")
    @GetMapping
    public ResponseEntity<List<Vehicule>> getAllVehicules() {
        return ResponseEntity.ok(
                vehiculeService.retrieveAllVehicules()
        );
    }

    @Operation(summary = "Rechercher un vehicule par ID")
    @ApiResponse(responseCode = "200", description = "Vehicule trouve")
    @ApiResponse(responseCode = "404", description = "Vehicule introuvable")
    @GetMapping("/{id}")
    public ResponseEntity<Vehicule> getVehicule(
            @PathVariable Long id) {

        Vehicule v = vehiculeService.retrieveVehicule(id);

        if (v == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(v);
    }

    @Operation(summary = "Modifier un vehicule")
    @ApiResponse(responseCode = "200", description = "Vehicule modifie")
    @ApiResponse(responseCode = "404", description = "Vehicule introuvable")
    @PutMapping("/{id}")
    public ResponseEntity<Vehicule> updateVehicule(
            @PathVariable Long id,
            @RequestBody Vehicule v) {

        Vehicule existant =
                vehiculeService.retrieveVehicule(id);

        if (existant == null) {
            return ResponseEntity.notFound().build();
        }

        existant.setImmatriculation(v.getImmatriculation());
        existant.setMarque(v.getMarque());
        existant.setModele(v.getModele());
        existant.setCategorie(v.getCategorie());
        existant.setTarifJournalier(v.getTarifJournalier());
        existant.setStatut(v.getStatut());

        return ResponseEntity.ok(
                vehiculeService.updateVehicule(existant)
        );
    }

    @Operation(summary = "Supprimer un vehicule")
    @ApiResponse(responseCode = "204", description = "Vehicule supprime")
    @ApiResponse(responseCode = "404", description = "Vehicule introuvable")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicule(
            @PathVariable Long id) {

        Vehicule v = vehiculeService.retrieveVehicule(id);

        if (v == null) {
            return ResponseEntity.notFound().build();
        }

        vehiculeService.removeVehicule(id);
        return ResponseEntity.noContent().build();
    }
}
