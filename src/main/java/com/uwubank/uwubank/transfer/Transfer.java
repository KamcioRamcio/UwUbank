package com.uwubank.uwubank.transfer;

import com.uwubank.uwubank.account.AccountType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@Table("transfers")
public class Transfer {
    @Id
    @Column("transfer_id")
    private Long transferId;

    @Column("customer_id")
    private Long senderId;

    @Column("sender_account_id")
    private Long senderAccountId;

    @Column("receiver_account_id")
    private Long receiverAccountId;

    private double amount;

    private Date date;

    public Transfer(Long senderId, Long senderAccountId, Long receiverAccountId, double amount, Date date) {
        this.senderId = senderId;
        this.senderAccountId = senderAccountId;
        this.receiverAccountId = receiverAccountId;
        this.amount = amount;
        this.date = date;
    }
}