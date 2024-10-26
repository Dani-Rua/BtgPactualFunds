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

@Document(collection = "Client")
public class Client {

    @Id
    private String id;

    private String name;

    private String phone;

}
