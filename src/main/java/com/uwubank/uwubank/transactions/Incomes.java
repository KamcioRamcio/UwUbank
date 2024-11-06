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
@Table("incomes")
public class Incomes {
    @Id
    @Column("income_id")
    private Long incomeId;

    @Column("customer_id")
    private Long customerId;

    private double amount;

    @Column("currency")
    private Currencies currency;

    private String sender;

    @Column("account_id")
    private Long accountId;

    private LocalDate date;

    public Incomes(Long customerId, double amount, Currencies currency, Long accountId, LocalDate date) {
        this.customerId = customerId;
        this.amount = amount;
        this.currency = currency;
        this.accountId = accountId;
        this.date = date;
    }
}