package com.uwubank.uwubank.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uwubank.uwubank.customer.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@Table("accounts")
public class Account {

    @Id
    @Column("account_id")
    private Long accountId;

    private double balance = 0.0;

    @Column("account_type")
    private AccountType accountType;

    @Column("customer_id")
    private Long customerId;

    @Transient
    @JsonIgnore
    private Customer customer;

    public Account(Long customerId, AccountType accountType) {
        this.customerId = customerId;
        this.accountType = accountType;
    }
}