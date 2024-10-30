package com.btgpactualfunds.funds.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FundSubscriptionRequestDTO {
    private int clientId;
    private int fundId;
    private float subscriptionAmount;
}
