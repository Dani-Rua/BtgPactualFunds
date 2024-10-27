package com.btgpactualfunds.funds.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class FundSubscriptionRequestDTO {
    private String clientId;
    private String fundId;
    private double amount;
}
