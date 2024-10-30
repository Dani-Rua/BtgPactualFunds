package com.btgpactualfunds.funds.services;

import com.btgpactualfunds.funds.dto.request.FundSubscriptionRequestDTO;
import com.btgpactualfunds.funds.dto.request.FundWithdrawalRequestDTO;
import com.btgpactualfunds.funds.entities.Client;
import com.btgpactualfunds.funds.entities.Fund;
import com.btgpactualfunds.funds.entities.SubscriptionFund;
import com.btgpactualfunds.funds.repository.ClientRepository;
import com.btgpactualfunds.funds.repository.FundsRepository;
import com.btgpactualfunds.funds.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    public static final int CLIENT_ID = 1;
    public static final int FUND_ID = 1;
    public static final int FUND_EXIST_ID = 2;

    //@MockBean
    @Mock
    ClientRepository clientRepository;

    //@MockBean
    @Mock
    FundsRepository fundsRepository;

    //@MockBean
    @Mock
    TransactionRepository transactionRepository;

    //@Autowired
    @InjectMocks
    ClientService clientService;

    @Test
    void successSubscribe() {
        FundSubscriptionRequestDTO request = new FundSubscriptionRequestDTO(CLIENT_ID, FUND_ID, 150);
        Client client = getClient(200);
        Fund fund = getFund(100);
        when(clientRepository.findById(request.getClientId())).thenReturn(Optional.of(client));
        when(fundsRepository.findById(request.getFundId())).thenReturn(Optional.of(fund));
        when(transactionRepository.count()).thenReturn(1L);

        Assertions.assertDoesNotThrow(() -> clientService.subscribeFund(request));

        verify(clientRepository, times(1)).findById(CLIENT_ID);
        verify(fundsRepository, times(1)).findById(FUND_ID);
        verify(transactionRepository, times(1)).count();
        verify(clientRepository, times(1)).save(any());
        verify(transactionRepository, times(1)).save(any());
    }

    @Test
    void unsubscribeFund(){
        FundWithdrawalRequestDTO request = new FundWithdrawalRequestDTO(CLIENT_ID, FUND_EXIST_ID);
        Client client = getClient(200);

        when(clientRepository.findById(request.getClientId())).thenReturn(Optional.of(client));
        when(transactionRepository.count()).thenReturn(1L);

        Assertions.assertDoesNotThrow(() -> clientService.unsubscribeFund(request));

        verify(clientRepository, times(1)).findById(CLIENT_ID);
        verify(transactionRepository, times(1)).count();
        verify(clientRepository, times(1)).save(any());
        verify(transactionRepository, times(1)).save(any());

    }
    private static Fund getFund(float minimumAmount) {
        return Fund.builder()
                .id(FUND_ID)
                .name("fund1")
                .category("category1")
                .minimumAmount(minimumAmount)
                .build();
    }

    private static Client getClient(float balance) {
        List<SubscriptionFund> subscriptions = new ArrayList<>();
        SubscriptionFund subscriptionFund = SubscriptionFund.builder()
                .id(FUND_EXIST_ID)
                .subscriptionAmount(300)
                .build();
        subscriptions.add(subscriptionFund);
        return Client.builder()
                .id(CLIENT_ID)
                .name("name1")
                .email("name1@gmail.com")
                .phone(300123456789L)
                .balance(balance)
                .notificationMethod("SMS")
                .funds(subscriptions)
                .build();
    }

    private int getNextId() {
        return (int) (transactionRepository.count() + 1);
    }
}
