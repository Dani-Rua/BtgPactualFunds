package com.btgpactualfunds.funds.repository;

import com.btgpactualfunds.funds.entities.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends MongoRepository<Client, Integer> {}
