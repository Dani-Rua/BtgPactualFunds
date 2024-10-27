package com.btgpactualfunds.funds.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Document(collection = "Funds")

public class Funds {
    @Id
    private String fundId;
    private String name;
    private String category;
    private String minimumAmount;
}
