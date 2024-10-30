package com.btgpactualfunds.funds.services;

import com.btgpactualfunds.funds.dto.request.FundSubscriptionRequestDTO;
import com.btgpactualfunds.funds.dto.request.FundWithdrawalRequestDTO;
import com.btgpactualfunds.funds.entities.*;
import com.btgpactualfunds.funds.exception.NotFoundClientException;
import com.btgpactualfunds.funds.exception.NotFoundFundException;
import com.btgpactualfunds.funds.repository.ClientRepository;
import com.btgpactualfunds.funds.repository.FundsRepository;
import com.btgpactualfunds.funds.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {
    @Autowired
    private final ClientRepository clientRepository;

    private final FundsRepository fundsRepository;

    private final TransactionRepository transactionRepository;

    @Autowired
    private EmailService emailService;

    public Response subscriptions(Integer clientId){
        Optional<Client> optionalClient = clientRepository.findById(clientId);
        if (optionalClient.isEmpty()) {
            throw new NotFoundClientException("El usuario no existe");
        }

        Client client = optionalClient.get();

        List<SubscriptionFund> subscriptionFunds = client.getFunds();

        if(subscriptionFunds.isEmpty()){
            return Response.builder()
                    .message("El cliente no tiene suscripciones")
                    .body(subscriptionFunds)
                    .build();
        }else{
            return Response.builder()
                    .message("Lista de suscripciones")
                    .body(subscriptionFunds)
                    .build();
        }

    }

    public Response subscribeFund(FundSubscriptionRequestDTO request) throws Exception {
        Optional<Client> optionalClient = clientRepository.findById(request.getClientId());
        if (optionalClient.isEmpty()) {
            throw new NotFoundClientException("El usuario no existe");
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

        emailService.sendEmail("drua@cidenet.com.co");

        return Response.builder()
                .message("Transaccion realizada con exito")
                .body(transaction)
                .build();
    }

    public Response unsubscribeFund(FundWithdrawalRequestDTO request) throws Exception {
        Optional<Client> optionalClient = clientRepository.findById(request.getClientId());
        if (optionalClient.isEmpty()){
            throw new NotFoundClientException("El usuario no existe");
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

        return Response.builder()
                .message("Cancelacion realizada con exito")
                .body(transaction)
                .build();
    }

    private int getNextId() {
        return (int) (transactionRepository.count() + 1);
    }
}
