package com.btgpactualfunds.funds.services;

import com.btgpactualfunds.funds.entities.Transaction;
import com.btgpactualfunds.funds.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    TransactionRepository transactionRepository;

    @InjectMocks
    TransactionService transactionService;

    public static final Integer CLIENTID = 1;

    @Test
    void getTransactions() {
        List<Transaction> transactionList = getClientTransactions();

        when(transactionRepository.findByClientId(CLIENTID)).thenReturn(transactionList);

        Assertions.assertDoesNotThrow(() -> transactionService.getTransactionsByClientId(CLIENTID));

        verify(transactionRepository, times(1)).findByClientId(CLIENTID);

    }

    private static List<Transaction> getClientTransactions() {
        return List.of(Transaction.builder()
                .transactionId(1)
                .clientId(1)
                .fundsId(1)
                .date(new Date())
                .type("ADD")
                .amount(150)
                .build());
    }
}
