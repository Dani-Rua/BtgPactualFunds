package com.btgpactualfunds.funds.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TransactionQueryResponseDTO {

    private List<TransactionDTO> transactions;

    @Getter
    @Setter
    public static class TransactionDTO {
        private String transactionId;
        private String fundId;
        private String transactionType;
        private double amount;

    }
}
