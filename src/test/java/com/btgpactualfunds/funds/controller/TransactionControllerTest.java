package com.btgpactualfunds.funds.controller;

import com.btgpactualfunds.funds.dto.request.FundWithdrawalRequestDTO;
import com.btgpactualfunds.funds.entities.Client;
import com.btgpactualfunds.funds.entities.Transaction;
import com.btgpactualfunds.funds.services.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    public static final int CLIENT_ID = 1;
    public static final String URL = "/api/transaction/client/";

    @MockBean
    TransactionService transactionService;
    @Autowired
    MockMvc mvc;


    @Test
    void getTransaction() throws Exception {
        mvc.perform(get(URL + CLIENT_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @ValueSource(classes = {Exception.class})
    void Exception(Class<? extends Throwable> exception) throws Exception {
        doThrow(exception).when(transactionService)
                .getTransactionsByClientId(any(Integer.class));

        mvc.perform(get(URL + CLIENT_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }
}
