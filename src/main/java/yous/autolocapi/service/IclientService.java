package yous.autolocapi.service;

import yous.autolocapi.domain.Client;

import java.util.List;

public interface IclientService {
    List<Client> retrieveAllClients();
    Client addClient(Client c);
    Client updateClient(Client c);
    Client retrieveClient(Long idClient);
    void removeClient(Long idClient);
    List<Client> addClients (List<Client> clients);
}
