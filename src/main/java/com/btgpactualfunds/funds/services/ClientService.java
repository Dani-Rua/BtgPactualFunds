package com.btgpactualfunds.funds.services;

import com.btgpactualfunds.funds.dto.request.FundSubscriptionRequestDTO;
import com.btgpactualfunds.funds.dto.request.FundWithdrawalRequestDTO;
import com.btgpactualfunds.funds.entities.Client;
import com.btgpactualfunds.funds.entities.Fund;
import com.btgpactualfunds.funds.entities.SubscriptionFund;
import com.btgpactualfunds.funds.entities.Transaction;
import com.btgpactualfunds.funds.exception.NotFoundClientException;
import com.btgpactualfunds.funds.exception.NotFoundFundException;
import com.btgpactualfunds.funds.repository.ClientRepository;
import com.btgpactualfunds.funds.repository.FundsRepository;

import com.btgpactualfunds.funds.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private FundsRepository fundsRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public void subscribeFund(FundSubscriptionRequestDTO request) throws Exception {
        Optional<Client> optionalClient = clientRepository.findById(request.getClientId());
        if (optionalClient.isEmpty()) {
            throw new NotFoundClientException();
        }

        Client client = optionalClient.get();
        Optional<Fund> optionalFund = fundsRepository.findById(request.getFundId());
        if (optionalFund.isEmpty()) {
            throw new NotFoundFundException();
        }

        Fund fund = optionalFund.get();
        fund.validateMinimumAmount(request.getSubscriptionAmount());

        SubscriptionFund subscription = new SubscriptionFund(request.getFundId(), request.getSubscriptionAmount());
        client.addSubscriptionFund(subscription);

        int next = getNextId();
        Transaction transaction = Transaction.builder()
                .transactionId(next)
                .clientId(client.getId())
                .fundsId(fund.getId())
                .type("ADD")
                .amount(request.getSubscriptionAmount())
                .notificationSent(false)
                .date(new Date())
                .build();

        clientRepository.save(client);
        transactionRepository.save(transaction);
    }

    public void unsubscribeFund(FundWithdrawalRequestDTO request) throws Exception {
        Optional<Client> optionalClient = clientRepository.findById(request.getClientId());
        if (optionalClient.isEmpty()){
            throw new NotFoundClientException();
        }

        Client client = optionalClient.get();

        SubscriptionFund fund = client.removeSubscriptionFund(request.getFundId());

        int next = getNextId();
        Transaction transaction = Transaction.builder()
                .transactionId(next)
                .clientId(client.getId())
                .fundsId(fund.getId())
                .type("REMOVE")
                .amount(fund.getSubscriptionAmount())
                .notificationSent(false)
                .date(new Date())
                .build();

        clientRepository.save(client);
        transactionRepository.save(transaction);
    }

    private int getNextId() {
        return (int) (transactionRepository.count() + 1);
    }
}
