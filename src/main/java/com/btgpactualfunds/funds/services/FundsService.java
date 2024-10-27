package com.btgpactualfunds.funds.services;

import com.btgpactualfunds.funds.entities.Funds;
import com.btgpactualfunds.funds.repository.FundsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class FundsService {
    @Autowired
    private FundsRepository fundsRepository;

    public Optional<Funds> findFund(String id){ return fundsRepository.findById(id);}
}
