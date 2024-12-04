package com.uwubank.uwubank.cards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uwubank.uwubank.account.Account;
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
@Table("cards")
public class  Card {

    @Id
    private Long cardId;

    @Column("customer_id")
    private Long customerId;

    @Column("account_id")
    private Long accountId;

    private String cardNumber;
    private CardType cardType;
    private Date expirationDate;
    private String cvv;
    private double limits;
    private String status;

    @Transient
    @JsonIgnore
    private Account account;
    @Transient
    @JsonIgnore
    private Customer customer;

    public Card(Long customerId, Long accountId, String cardNumber, CardType cardType, Date expirationDate, String cvv, double limits, String status) {
        this.customerId = customerId;
        this.accountId = accountId;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.limits = limits;
        this.status = status;
    }

}
