package com.btgpactualfunds.funds.services;

import com.btgpactualfunds.funds.entities.Client;
import com.btgpactualfunds.funds.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Optional<Client> findClient(String id) {return clientRepository.findById(id);}

    public Client addClient(Client client) {return clientRepository.save(client);}

    public Client updateClient(Client client) {return clientRepository.save(client);}

    public List<Client> findAllClients() {return clientRepository.findAll();}

    public void deleteClient(String id){clientRepository.deleteById(id);}

    //TODO: Crear dos métodos 1. vincularse a un fondo, 2. y desvincularse de un fondo.

}
