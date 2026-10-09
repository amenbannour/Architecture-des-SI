
package yous.autolocapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import yous.autolocapi.domain.Agence;
import yous.autolocapi.service.IAgenceService;

import java.util.List;

@RestController
@RequestMapping("/agences")
@RequiredArgsConstructor
public class AgenceController {

    private final IAgenceService agenceService;

    // CREATE : Ajouter une agence
    @PostMapping
    public ResponseEntity<Agence> addAgence(
            @RequestBody Agence agence) {

        agence.setIdagence(null);

        Agence nouvelleAgence =
                agenceService.addAgence(agence);

        return ResponseEntity.ok(nouvelleAgence);
    }

    // READ : Recuperer toutes les agences
    @GetMapping
    public ResponseEntity<List<Agence>> getAllAgences() {
        return ResponseEntity.ok(
                agenceService.retrieveAllAgences()
        );
    }

    // READ : Recuperer une agence par ID
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
    @PostMapping("/multiple")
    public ResponseEntity<List<Agence>> addAgences(
            @RequestBody List<Agence> agences) {

        agences.forEach(a -> a.setIdagence(null));

        return ResponseEntity.ok(
                agenceService.addAgences(agences)
        );
    }
}
