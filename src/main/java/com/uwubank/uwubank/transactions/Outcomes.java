package com.uwubank.uwubank.transactions;

import com.uwubank.uwubank.account.AccountType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Table("outcomes")
public class Outcomes {
    @Id
    @Column("outcome_id")
    private Long outcomeId;

    @Column("customer_id")
    private Long customerId;

    private double amount;

    @Column("currency")
    private Currencies currency;

    private String receiver;

    @Column("account_id")
    private Long accountId;

    private LocalDate date;

    public Outcomes(Long customerId, double amount, Currencies currency,Long accountId, LocalDate date) {
        this.customerId = customerId;
        this.amount = amount;
        this.currency = currency;
        this.accountId = accountId;
        this.date = date;
    }
}

// TODO: Add description to the Outcomes and Incomes