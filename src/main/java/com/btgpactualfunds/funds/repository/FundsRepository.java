package com.btgpactualfunds.funds.repository;

import com.btgpactualfunds.funds.entities.Fund;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FundsRepository extends MongoRepository<Fund, Integer> {}
