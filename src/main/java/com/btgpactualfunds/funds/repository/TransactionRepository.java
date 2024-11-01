package com.btgpactualfunds.funds.repository;

import com.btgpactualfunds.funds.entities.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, Integer> {
    List<Transaction> findByClientId(Integer clientId);
}
