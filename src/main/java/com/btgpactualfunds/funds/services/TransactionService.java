package com.btgpactualfunds.funds.services;

import com.btgpactualfunds.funds.entities.Transaction;
import com.btgpactualfunds.funds.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getTransactionsByClientId(Integer clientId) throws Exception{
        return transactionRepository.findByClientId(clientId);
    }
}
