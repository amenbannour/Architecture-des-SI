
package yous.autolocapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import yous.autolocapi.domain.Agence;
import yous.autolocapi.service.IAgenceService;

import java.util.List;

@RestController
@RequestMapping("/agences")
@RequiredArgsConstructor
@Tag(
        name = "Agences",
        description = "API REST pour la gestion des agences AutoLoc"
)
public class AgenceController {

    private final IAgenceService agenceService;

    // CREATE : Ajouter une agence
    @Operation(
            summary = "Ajouter une agence",
            description = "Enregistre une nouvelle agence dans la base de donnees"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Agence ajoutee avec succes"
    )
    @PostMapping
    public ResponseEntity<Agence> addAgence(
            @RequestBody Agence agence) {

        agence.setIdagence(null);

        Agence nouvelleAgence =
                agenceService.addAgence(agence);

        return ResponseEntity.ok(nouvelleAgence);
    }

    // READ : Recuperer toutes les agences
    @Operation(
            summary = "Afficher toutes les agences",
            description = "Retourne la liste de toutes les agences"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Liste des agences recuperee avec succes"
    )
    @GetMapping
    public ResponseEntity<List<Agence>> getAllAgences() {

        return ResponseEntity.ok(
                agenceService.retrieveAllAgences()
        );
    }

    // READ : Recuperer une agence par ID
    @Operation(
            summary = "Rechercher une agence par ID",
            description = "Retourne une agence selon son identifiant"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Agence trouvee"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Agence introuvable"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Agence> getAgence(
            @PathVariable Long id) {

        Agence agence = agenceService.retrieveAgence(id);

        if (agence == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(agence);
    }

    // UPDATE : Modifier une agence
    @Operation(
            summary = "Modifier une agence",
            description = "Modifie les informations d'une agence existante"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Agence modifiee avec succes"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Agence introuvable"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Agence> updateAgence(
            @PathVariable Long id,
            @RequestBody Agence agence) {

        Agence agenceExistante =
                agenceService.retrieveAgence(id);

        if (agenceExistante == null) {
            return ResponseEntity.notFound().build();
        }

        agenceExistante.setNom(agence.getNom());
        agenceExistante.setVille(agence.getVille());
        agenceExistante.setAdresse(agence.getAdresse());
        agenceExistante.setTelephone(agence.getTelephone());

        Agence agenceModifiee =
                agenceService.updateAgence(agenceExistante);

        return ResponseEntity.ok(agenceModifiee);
    }

    // DELETE : Supprimer une agence
    @Operation(
            summary = "Supprimer une agence",
            description = "Supprime une agence selon son identifiant"
    )
    @ApiResponse(
            responseCode = "204",
            description = "Agence supprimee avec succes"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Agence introuvable"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgence(
            @PathVariable Long id) {

        Agence agence = agenceService.retrieveAgence(id);

        if (agence == null) {
            return ResponseEntity.notFound().build();
        }

        agenceService.removeAgence(id);

        return ResponseEntity.noContent().build();
    }

    // CREATE : Ajouter plusieurs agences
    @Operation(
            summary = "Ajouter plusieurs agences",
            description = "Enregistre plusieurs agences en une seule requete"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Agences ajoutees avec succes"
    )
    @PostMapping("/multiple")
    public ResponseEntity<List<Agence>> addAgences(
            @RequestBody List<Agence> agences) {

        agences.forEach(a -> a.setIdagence(null));

        return ResponseEntity.ok(
                agenceService.addAgences(agences)
        );
    }
}
