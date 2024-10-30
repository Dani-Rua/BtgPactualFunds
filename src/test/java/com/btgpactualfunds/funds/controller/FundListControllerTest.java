package com.btgpactualfunds.funds.controller;

import com.btgpactualfunds.funds.dto.request.FundSubscriptionRequestDTO;
import com.btgpactualfunds.funds.dto.request.FundWithdrawalRequestDTO;
import com.btgpactualfunds.funds.entities.Client;
import com.btgpactualfunds.funds.entities.Fund;
import com.btgpactualfunds.funds.exception.NotEnoughtBalanceException;
import com.btgpactualfunds.funds.exception.NotFoundClientException;
import com.btgpactualfunds.funds.exception.NotFoundFundException;
import com.btgpactualfunds.funds.repository.FundsRepository;
import com.btgpactualfunds.funds.services.ClientService;
import com.btgpactualfunds.funds.services.FundService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FundListController.class)

public class FundListControllerTest {
    public static final String URL = "/api/funds";

    @Autowired
    MockMvc mvc;

    @MockBean
    FundService fundService;

    @Test
    void getFunds() throws Exception {
        mvc.perform(get(URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
