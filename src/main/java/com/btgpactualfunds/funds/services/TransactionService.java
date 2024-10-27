package com.btgpactualfunds.funds.services;

import com.btgpactualfunds.funds.entities.Transaction;
import com.btgpactualfunds.funds.repository.ClientRepository;
import com.btgpactualfunds.funds.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getTransactionsByClientId(String clientId) {
        return transactionRepository.findByClientId(clientId);
    }

    public List<Transaction> getTransactionHistory(String clientId) {
        // Implementar la lógica para consultar el historial de transacciones
        return null;
    }


}
