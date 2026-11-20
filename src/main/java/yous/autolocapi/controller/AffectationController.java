
package yous.autolocapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import yous.autolocapi.domain.Employe;
import yous.autolocapi.domain.Vehicule;
import yous.autolocapi.service.IEmployeService;
import yous.autolocapi.service.IVehiculeService;

@RestController
@RequestMapping("/affectations")
@RequiredArgsConstructor
@Tag(name = "Affectations", description = "Gestion des relations JPA")
public class AffectationController {

    private final IEmployeService employeService;
    private final IVehiculeService vehiculeService;

    // 1. Desaffecter un employe
    @Operation(summary = "Desaffecter un employe de son agence")
    @ApiResponse(responseCode = "200",
            description = "Employe desaffecte")
    @PutMapping("/employes/{idEmploye}/desaffecter")
    public ResponseEntity<Employe> desaffecterEmploye(
            @PathVariable Long idEmploye) {

        return ResponseEntity.ok(
                employeService.desaffecterEmploye(idEmploye)
        );
    }

    // 2. Affecter un employe a une agence
    @Operation(summary = "Affecter un employe a une agence")
    @ApiResponse(responseCode = "200",
            description = "Employe affecte")
    @PutMapping("/employes/{idEmploye}/agences/{idAgence}")
    public ResponseEntity<Employe> affecterEmploye(
            @PathVariable Long idEmploye,
            @PathVariable Long idAgence) {

        return ResponseEntity.ok(
                employeService.affecterEmploye(idEmploye, idAgence)
        );
    }

    // 3. Affecter un vehicule a une agence
    @Operation(summary = "Affecter un vehicule a une agence")
    @ApiResponse(responseCode = "200",
            description = "Vehicule affecte")
    @PutMapping("/vehicules/{idVehicule}/agences/{idAgence}")
    public ResponseEntity<Vehicule> affecterVehicule(
            @PathVariable Long idVehicule,
            @PathVariable Long idAgence) {

        return ResponseEntity.ok(
                vehiculeService.affecterVehicule(idVehicule, idAgence)
        );
    }

    // 4. Affecter un equipement a un vehicule
    @Operation(summary = "Ajouter un equipement a un vehicule")
    @ApiResponse(responseCode = "200",
            description = "Equipement affecte")
    @PutMapping("/vehicules/{idVehicule}/equipements/{idEquipement}")
    public ResponseEntity<Vehicule> affecterEquipement(
            @PathVariable Long idVehicule,
            @PathVariable Long idEquipement) {

        return ResponseEntity.ok(
                vehiculeService.affecterEquipement(
                        idVehicule, idEquipement)
        );
    }

    // 5. Desaffecter un equipement
    @Operation(summary = "Retirer un equipement d'un vehicule")
    @ApiResponse(responseCode = "200",
            description = "Equipement desaffecte")
    @DeleteMapping("/vehicules/{idVehicule}/equipements/{idEquipement}")
    public ResponseEntity<Vehicule> desaffecterEquipement(
            @PathVariable Long idVehicule,
            @PathVariable Long idEquipement) {

        return ResponseEntity.ok(
                vehiculeService.desaffecterEquipement(
                        idVehicule, idEquipement)
        );
    }
}
