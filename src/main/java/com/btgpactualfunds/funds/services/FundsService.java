package com.btgpactualfunds.funds.services;

import com.btgpactualfunds.funds.entities.Fund;
import com.btgpactualfunds.funds.repository.FundsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class FundsService {
    @Autowired
    private FundsRepository fundsRepository;

    public Optional<Fund> findFund(Integer id){ return fundsRepository.findById(id);}
}
