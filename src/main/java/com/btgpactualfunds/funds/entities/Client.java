package com.btgpactualfunds.funds.entities;


import com.btgpactualfunds.funds.exception.FundNotExistsException;
import com.btgpactualfunds.funds.exception.FundExistsException;
import com.btgpactualfunds.funds.exception.NotEnoughtBalanceException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Client")
public class Client {
    @Id
    private int id;
    private String name;
    private String email;
    private long phone;
    private float balance;
    private List<SubscriptionFund> funds;
    private String notificationMethod;

    public void addSubscriptionFund(SubscriptionFund fund) throws FundExistsException, NotEnoughtBalanceException {
        validateInitFunds();
        SubscriptionFund clientFund = getFundSubscriptionFromClient(fund.getId());
        if (clientFund != null) {
            throw new FundExistsException();
        }
        balance -= fund.getSubscriptionAmount();
        if (balance < 0){
            throw new NotEnoughtBalanceException();
        }
        funds.add(fund);
    }

    public SubscriptionFund removeSubscriptionFund(int fundId) throws FundNotExistsException {
        validateInitFunds();
        SubscriptionFund clientFund = getFundSubscriptionFromClient(fundId);
        if (clientFund == null) {
            throw new FundNotExistsException();
        }
        balance += clientFund.getSubscriptionAmount();
        funds.remove(clientFund);
        return clientFund;
    }

    public SubscriptionFund getFundSubscriptionFromClient(int fundId) {
        for (SubscriptionFund fund: funds) {
            if (fund.getId() == fundId){
                return fund;
            }
        }
        return null;
    }

    private void validateInitFunds() {
        if (funds == null) {
            funds = new ArrayList<>();
        }
    }
}

