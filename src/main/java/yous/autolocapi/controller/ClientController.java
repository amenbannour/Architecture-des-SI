
package yous.autolocapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import yous.autolocapi.domain.Client;
import yous.autolocapi.service.IclientService;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@Tag(
        name = "Clients",
        description = "API REST pour la gestion des clients AutoLoc"
)
public class ClientController {

    private final IclientService clientService;

    // CREATE : Ajouter un client
    @Operation(
            summary = "Ajouter un client",
            description = "Enregistre un nouveau client dans la base de donnees"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Client ajoute avec succes"
    )
    @PostMapping
    public ResponseEntity<Client> addClient(
            @RequestBody Client client) {

        client.setIdClient(null);

        Client nouveauClient = clientService.addClient(client);

        return ResponseEntity.ok(nouveauClient);
    }

    // READ : Recuperer tous les clients
    @Operation(
            summary = "Afficher tous les clients",
            description = "Retourne la liste de tous les clients"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Liste des clients recuperee avec succes"
    )
    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {

        return ResponseEntity.ok(
                clientService.retrieveAllClients()
        );
    }

    // READ : Recuperer un client par ID
    @Operation(
            summary = "Rechercher un client par ID",
            description = "Retourne un client selon son identifiant"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Client trouve"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Client introuvable"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClient(
            @PathVariable Long id) {

        Client client = clientService.retrieveClient(id);

        if (client == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(client);
    }

    // UPDATE : Modifier un client
    @Operation(
            summary = "Modifier un client",
            description = "Modifie les informations d'un client existant"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Client modifie avec succes"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Client introuvable"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(
            @PathVariable Long id,
            @RequestBody Client client) {

        Client clientExistant = clientService.retrieveClient(id);

        if (clientExistant == null) {
            return ResponseEntity.notFound().build();
        }

        clientExistant.setNom(client.getNom());
        clientExistant.setPrenom(client.getPrenom());
        clientExistant.setEmail(client.getEmail());
        clientExistant.setTelephone(client.getTelephone());
        clientExistant.setNumPermis(client.getNumPermis());
        clientExistant.setDateInscription(
                client.getDateInscription()
        );

        Client clientModifie =
                clientService.updateClient(clientExistant);

        return ResponseEntity.ok(clientModifie);
    }

    // DELETE : Supprimer un client
    @Operation(
            summary = "Supprimer un client",
            description = "Supprime un client selon son identifiant"
    )
    @ApiResponse(
            responseCode = "204",
            description = "Client supprime avec succes"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Client introuvable"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(
            @PathVariable Long id) {

        Client client = clientService.retrieveClient(id);

        if (client == null) {
            return ResponseEntity.notFound().build();
        }

        clientService.removeClient(id);

        return ResponseEntity.noContent().build();
    }

    // CREATE : Ajouter plusieurs clients
    @Operation(
            summary = "Ajouter plusieurs clients",
            description = "Enregistre plusieurs clients en une seule requete"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Clients ajoutes avec succes"
    )
    @PostMapping("/multiple")
    public ResponseEntity<List<Client>> addClients(
            @RequestBody List<Client> clients) {

        clients.forEach(client -> client.setIdClient(null));

        return ResponseEntity.ok(
                clientService.addClients(clients)
        );
    }
}
