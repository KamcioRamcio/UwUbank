package com.uwubank.uwubank.savings;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uwubank.uwubank.account.AccountType;
import com.uwubank.uwubank.customer.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@Table("savings")
public class Savings {

    @Id
    private Long savingsId;

    @Column("customer_id")
    private Long customerId;

    private double balance;
    private AccountType currency;
    private double interestRate;

    @Transient
    @JsonIgnore
    private Customer customer;


    public Savings(Long customerId, double balance, AccountType currency, double interestRate) {
        this.customerId = customerId;
        this.balance = balance;
        this.currency = currency;
        this.interestRate = interestRate;
    }

}
