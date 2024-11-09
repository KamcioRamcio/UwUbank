package com.uwubank.uwubank.loans;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uwubank.uwubank.account.AccountType;
import com.uwubank.uwubank.customer.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@NoArgsConstructor
@Table("loans")
public class Loan {
    @Id
    private Long loanId;

    @Column("customer_id")
    private Long customerId;

    private double amount;
    private AccountType currency;
    private Date startDate;
    private Date endDate;
    private double interestRate;
    private boolean approved;
    private String status;

    @Transient
    @JsonIgnore
    private Customer customer;

    public Loan(Long customerId, double amount, AccountType currency, Date startDate, Date endDate, double interestRate, String status) {
        this.customerId = customerId;
        this.amount = amount;
        this.currency = currency;
        this.startDate = startDate;
        this.endDate = endDate;
        this.interestRate = interestRate;
        this.approved = false;
        this.status = status;
    }

}

