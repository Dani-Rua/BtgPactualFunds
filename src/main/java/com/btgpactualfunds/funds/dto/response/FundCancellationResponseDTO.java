package com.btgpactualfunds.funds.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class FundCancellationResponseDTO {

    private int transactionId;
    private int message;
    private double refundedAmount;
}
