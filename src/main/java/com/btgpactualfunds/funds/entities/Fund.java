package com.btgpactualfunds.funds.entities;


import com.btgpactualfunds.funds.exception.MinimumAmountException;
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
public class Fund {
    @Id
    private int id;
    private String name;
    private String category;
    private float minimumAmount;

    public void validateMinimumAmount(float amount) throws MinimumAmountException {
        if (minimumAmount > amount) {
            throw new MinimumAmountException();
        }
    }
}
