package com.btgpactualfunds.funds.controller;

import com.btgpactualfunds.funds.dto.request.FundSubscriptionRequestDTO;
import com.btgpactualfunds.funds.dto.request.FundWithdrawalRequestDTO;
import com.btgpactualfunds.funds.entities.Client;
import com.btgpactualfunds.funds.exception.NotEnoughtBalanceException;
import com.btgpactualfunds.funds.exception.NotFoundClientException;
import com.btgpactualfunds.funds.exception.NotFoundFundException;
import com.btgpactualfunds.funds.repository.ClientRepository;
import com.btgpactualfunds.funds.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping("/client/fund")
    public ResponseEntity <Optional<Client>> subscribe(@RequestBody FundSubscriptionRequestDTO request){
        try {
            clientService.subscribeFund(request);
        } catch (NotFoundClientException | NotFoundFundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (NotEnoughtBalanceException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/client/fund")
    public ResponseEntity <Optional<Client>> unsubscribe(@RequestBody FundWithdrawalRequestDTO request){
        try {
            clientService.unsubscribeFund(request);
        } catch (NotFoundClientException | NotFoundFundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
