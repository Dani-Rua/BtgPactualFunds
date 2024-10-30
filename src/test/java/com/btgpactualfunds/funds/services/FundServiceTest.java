package com.btgpactualfunds.funds.services;

import com.btgpactualfunds.funds.entities.Fund;
import com.btgpactualfunds.funds.entities.Transaction;
import com.btgpactualfunds.funds.repository.FundsRepository;
import com.btgpactualfunds.funds.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FundServiceTest {

    @Mock
    FundsRepository fundsRepository;

    @InjectMocks
    FundService fundService;


    @Test
    void getFunds() {
        List<Fund> fundList = getListFunds();

        when(fundsRepository.findAll()).thenReturn(fundList);

        Assertions.assertDoesNotThrow(() -> fundService.getAllFunds());

        verify(fundsRepository, times(1)).findAll();

    }

    private static List<Fund> getListFunds() {
        return List.of(Fund.builder()
                        .id(1)
                        .minimumAmount(200)
                .build());
    }
}
