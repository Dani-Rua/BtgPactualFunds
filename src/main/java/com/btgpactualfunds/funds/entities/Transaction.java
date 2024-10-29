package com.btgpactualfunds.funds.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Transaction")
@Builder
public class Transaction {
    @Id
    private int transactionId;
    private int clientId;
    private int fundsId;
    private String type;
    private float amount;
    private boolean notificationSent;
    private Date date;
}
