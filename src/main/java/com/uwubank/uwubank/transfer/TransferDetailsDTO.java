package com.uwubank.uwubank.transfer;

import lombok.Data;

import java.util.Date;

@Data
public class TransferDetailsDTO {
    private Long transferId;
    private Long transferCustomerId;
    private double transferAmount;
    private Date transferDate;
    private Long ownTransferId;
    private Long ownTransferCustomerId;
    private double ownTransferAmount;
    private Date ownTransferDate;
    private Long incomeId;
    private Long incomeCustomerId;
    private double incomeAmount;
    private Date incomeDate;
    private Long outcomeId;
    private Long outcomeCustomerId;
    private double outcomeAmount;
    private Date outcomeDate;
}