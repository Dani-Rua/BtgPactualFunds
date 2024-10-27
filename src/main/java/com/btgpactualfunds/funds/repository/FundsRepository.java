package com.btgpactualfunds.funds.repository;

import com.btgpactualfunds.funds.entities.Funds;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FundsRepository extends MongoRepository<Funds, String> {}
