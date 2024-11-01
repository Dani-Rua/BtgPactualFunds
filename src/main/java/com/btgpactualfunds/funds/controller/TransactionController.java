package com.btgpactualfunds.funds.controller;

import com.btgpactualfunds.funds.entities.Transaction;
import com.btgpactualfunds.funds.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping("/transaction/client/{id}")
    public ResponseEntity<List<Transaction>> getTransaction(@PathVariable int id){
        try {
            List<Transaction> transactionList= transactionService.getTransactionsByClientId(id);
            return new ResponseEntity<>(transactionList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
