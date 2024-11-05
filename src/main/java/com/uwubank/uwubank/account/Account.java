package com.uwubank.uwubank.account;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Data
@NoArgsConstructor
@Table("accounts")
public class Account {

    @Id
    private Long accountId;

    private double balance = 0.0;
    private String accountType;
    private Long customerId;

    public Account(Long customerId, String accountType) {
        this.customerId = customerId;
        this.accountType = accountType;
    }
}
