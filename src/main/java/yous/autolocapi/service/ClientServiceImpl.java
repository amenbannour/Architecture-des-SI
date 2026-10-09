
package yous.autolocapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import yous.autolocapi.domain.Client;
import yous.autolocapi.repository.ClientRepository;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IclientService {

    private final ClientRepository clientRepository;

    // READ : Recuperer tous les clients
    @Override
    public List<Client> retrieveAllClients() {
        return StreamSupport.stream(
                clientRepository.findAll().spliterator(),
                false
        ).toList();
    }

    // CREATE : Ajouter un client
    @Override
    public Client addClient(Client c) {
        c.setIdClient(null);
        return clientRepository.save(c);
    }

    // UPDATE : Modifier un client existant
    @Override
    public Client updateClient(Client c) {

        if (c.getIdClient() == null ||
                !clientRepository.existsById(c.getIdClient())) {
            throw new IllegalArgumentException(
                    "Client inexistant"
            );
        }

        return clientRepository.save(c);
    }

    // READ : Recuperer un client par ID
    @Override
    public Client retrieveClient(Long idClient) {
        return clientRepository.findById(idClient)
                .orElse(null);
    }

    // DELETE : Supprimer un client
    @Override
    public void removeClient(Long idClient) {

        if (!clientRepository.existsById(idClient)) {
            throw new IllegalArgumentException(
                    "Client inexistant"
            );
        }

        clientRepository.deleteById(idClient);
    }

    // CREATE : Ajouter plusieurs clients
    @Override
    public List<Client> addClients(List<Client> clients) {

        clients.forEach(c -> c.setIdClient(null));

        return StreamSupport.stream(
                clientRepository.saveAll(clients).spliterator(),
                false
        ).toList();
    }
}
