package com.btgpactualfunds.funds.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor

public class FundWithdrawalRequestDTO {
    private int clientId;
    private int fundId;
}
