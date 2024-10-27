package com.btgpactualfunds.funds.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Document(collection = "Funds")

public class Transaction {
    @Id
    private String transactionId;
    private Date date;
    private String type;
    private Funds fundsId;
    private BigDecimal amount;
    private boolean notificationSent;
    private String clientId;
}
