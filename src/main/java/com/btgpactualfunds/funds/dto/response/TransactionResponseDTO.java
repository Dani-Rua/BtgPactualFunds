package com.btgpactualfunds.funds.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TransactionResponseDTO {

    private List<TransactionDTO> transactions;

    @Getter
    @Setter
    public static class TransactionDTO {
        private int transactionId;
        private int fundId;
        private String transactionType;
        private float amount;
        private Date date;
    }
}
