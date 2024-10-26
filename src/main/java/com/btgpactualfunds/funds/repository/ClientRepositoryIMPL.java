package com.btgpactualfunds.funds.repository;

import com.btgpactualfunds.funds.entities.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClientRepositoryIMPL {

    @Autowired
    private ClientRepository repository;
    public Optional<Client> findClient(String id){ return repository.findById(id);}
    public Client addClient(Client client) {
        return repository.save(client);
    }

    public Client updateClient(Client client) { return repository.save(client);}
    public List<Client> findAllClient(){
        return repository.findAll();
    }
    public void deleteClient(String id){
        repository.deleteById(id);
    }


}
