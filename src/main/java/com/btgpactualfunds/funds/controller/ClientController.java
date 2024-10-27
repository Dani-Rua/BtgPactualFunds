package com.btgpactualfunds.funds.controller;


import com.btgpactualfunds.funds.entities.Client;
import com.btgpactualfunds.funds.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/client/find/{id}")
    public ResponseEntity <Optional<Client>> findClient(@PathVariable String id) {
        Optional<Client> findClient=this.clientRepository.findById(id);
        return new ResponseEntity<Optional<Client>>(findClient, HttpStatus.CREATED);
    }

    @GetMapping("/client/find-all")
    public ResponseEntity <List<Client>> findAllClient() {
        List<Client> findAllClient=this.clientRepository.findAll();
        return new ResponseEntity<List<Client>>(findAllClient, HttpStatus.OK);
    }

    @PostMapping("/client/add")
    public ResponseEntity <Client> addClient(@RequestBody Client client) {
        Client addClient=this.clientRepository.save(client);
        return new ResponseEntity<Client>(addClient, HttpStatus.CREATED);
    }

    @PutMapping("/client/update")
    public ResponseEntity <Client> updateClient(@RequestBody Client client) {
        Client updateClient=this.clientRepository.save(client);
        return new ResponseEntity<Client>(updateClient, HttpStatus.CREATED);
    }

    @DeleteMapping("/client/delete/{id}")
    public ResponseEntity deleteClient(@PathVariable String id) {
        this.clientRepository.deleteById(id);
        return new ResponseEntity(null, HttpStatus.OK);
    }
}
