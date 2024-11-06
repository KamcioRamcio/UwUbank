package com.uwubank.uwubank.transfer;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@Table("own_transfers")
public class OwnTransfer {
    @Id
    @Column("own_transfer_id")
    private Long ownTransferId;

    @Column("customer_id")
    private Long customerId;

    private double amount;

    @Column("from_account_id")
    private Long fromAccountId;

    @Column("to_account_id")
    private Long toAccountId;

    private Date date;

    public OwnTransfer(Long customerId, double amount, Long fromAccountId, Long toAccountId, Date date) {
        this.customerId = customerId;
        this.amount = amount;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.date = date;
    }
}