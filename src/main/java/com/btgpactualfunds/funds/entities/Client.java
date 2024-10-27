package com.btgpactualfunds.funds.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Document(collection = "Client")

public class Client {
    @Id
    private String id;
    private String name;
    private String email;
    private String phone;
    private BigDecimal currentBalance;
    private Funds fundId;
    private String notificationMethod;
}
