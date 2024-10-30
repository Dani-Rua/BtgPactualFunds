package com.btgpactualfunds.funds.controller;

import com.btgpactualfunds.funds.dto.request.FundSubscriptionRequestDTO;
import com.btgpactualfunds.funds.dto.request.FundWithdrawalRequestDTO;
import com.btgpactualfunds.funds.entities.Client;
import com.btgpactualfunds.funds.exception.NotEnoughtBalanceException;
import com.btgpactualfunds.funds.exception.NotFoundClientException;
import com.btgpactualfunds.funds.exception.NotFoundFundException;
import com.btgpactualfunds.funds.services.ClientService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {
    public static final int CLIENT_ID = 1;

    public static final String URL = "/api/client/fund";
    @MockBean
    ClientService clientService;

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    void successSubscription() throws Exception {
        Client client = getClient(100);

        mvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(client)))
                .andExpect(status().isCreated());
    }

    @ParameterizedTest
    @ValueSource(classes = {NotFoundClientException.class, NotFoundFundException.class})
    void NotFoundClientExceptionSubscription(Class<? extends Throwable> exception) throws Exception {
        Client client = getClient(100);
        doThrow(exception).when(clientService)
                .subscribeFund(any(FundSubscriptionRequestDTO.class));

        mvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(client)))
                .andExpect(status().isNotFound());
    }

    @ParameterizedTest
    @ValueSource(classes = {NotEnoughtBalanceException.class})
    void NotEnoughtBalanceException(Class<? extends Throwable> exception) throws Exception {
        Client client = getClient(100);
        doThrow(exception).when(clientService)
                .subscribeFund(any(FundSubscriptionRequestDTO.class));

        mvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(client)))
                .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @ValueSource(classes = {Exception.class})
    void Exception(Class<? extends Throwable> exception) throws Exception {
        Client client = getClient(100);
        doThrow(exception).when(clientService)
                .subscribeFund(any(FundSubscriptionRequestDTO.class));

        mvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(client)))
                .andExpect(status().isInternalServerError());
    }


    @Test
    void unSubscription() throws Exception {
        Client client = getClient(100);

        mvc.perform(delete(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(client)))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @ValueSource(classes = {NotFoundClientException.class, NotFoundFundException.class})
    void NotFoundClientExceptionUnSubscription(Class<? extends Throwable> exception) throws Exception {
        Client client = getClient(100);
        doThrow(exception).when(clientService)
                .unsubscribeFund(any(FundWithdrawalRequestDTO.class));

        mvc.perform(delete(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(client)))
                .andExpect(status().isNotFound());
    }

    @ParameterizedTest
    @ValueSource(classes = {Exception.class})
    void ExceptionUnSubscription(Class<? extends Throwable> exception) throws Exception {
        Client client = getClient(100);
        doThrow(exception).when(clientService)
                .unsubscribeFund(any(FundWithdrawalRequestDTO.class));

        mvc.perform(delete(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(client)))
                .andExpect(status().isInternalServerError());
    }



    private static Client getClient(float balance) {
        return Client.builder()
                .id(CLIENT_ID)
                .name("name1")
                .email("name1@gmail.com")
                .phone(300123456789L)
                .balance(balance)
                .notificationMethod("SMS")
                .build();
    }
}
