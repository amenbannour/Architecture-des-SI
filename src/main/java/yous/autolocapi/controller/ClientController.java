
package yous.autolocapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import yous.autolocapi.domain.Client;
import yous.autolocapi.service.IclientService;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final IclientService clientService;

    // CREATE : Ajouter un client
    @PostMapping
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        client.setIdClient(null);
        Client nouveauClient = clientService.addClient(client);
        return ResponseEntity.ok(nouveauClient);
    }

    // READ : Recuperer tous les clients
    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(
                clientService.retrieveAllClients()
        );
    }

    // READ : Recuperer un client par ID
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClient(@PathVariable Long id) {

        Client client = clientService.retrieveClient(id);

        if (client == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(client);
    }

    // UPDATE : Modifier un client
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
        clientExistant.setDateInscription(client.getDateInscription());

        Client clientModifie =
                clientService.updateClient(clientExistant);

        return ResponseEntity.ok(clientModifie);
    }

    // DELETE : Supprimer un client
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {

        Client client = clientService.retrieveClient(id);

        if (client == null) {
            return ResponseEntity.notFound().build();
        }

        clientService.removeClient(id);

        return ResponseEntity.noContent().build();
    }

    // CREATE : Ajouter plusieurs clients
    @PostMapping("/multiple")
    public ResponseEntity<List<Client>> addClients(
            @RequestBody List<Client> clients) {

        clients.forEach(client -> client.setIdClient(null));

        return ResponseEntity.ok(
                clientService.addClients(clients)
        );
    }
}
