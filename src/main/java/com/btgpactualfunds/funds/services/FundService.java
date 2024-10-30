package com.btgpactualfunds.funds.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btgpactualfunds.funds.entities.Fund;
import com.btgpactualfunds.funds.repository.FundsRepository;


@Service
public class FundService {

    @Autowired
    private FundsRepository fundsRepository;
    
    public List<Fund> getAllFunds() {
        return fundsRepository.findAll();
    }

}
