package com.btgpactualfunds.funds.controller;

import com.btgpactualfunds.funds.dto.request.FundSubscriptionRequestDTO;
import com.btgpactualfunds.funds.dto.request.FundWithdrawalRequestDTO;
import com.btgpactualfunds.funds.entities.Response;
import com.btgpactualfunds.funds.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/client/fund")
    public Response subscribe(@RequestBody FundSubscriptionRequestDTO request) throws Exception {
        return clientService.subscribeFund(request);
    }

    @DeleteMapping("/client/fund")
    public Response unsubscribe(@RequestBody FundWithdrawalRequestDTO request) throws Exception {
        return clientService.unsubscribeFund(request);
    }

    @GetMapping("/client/subscriptions")
    public Response subscriptions(@RequestParam int clientId){
        return clientService.subscriptions(clientId);
    }
}
