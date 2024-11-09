package com.uwubank.uwubank.statement;

import com.uwubank.uwubank.transactions.Incomes;
import com.uwubank.uwubank.transactions.Outcomes;
import com.uwubank.uwubank.transfer.Transfer;
import com.uwubank.uwubank.transfer.OwnTransfer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Statements {
    private Long accountId;
    private List<Incomes> incomes;
    private List<Outcomes> outcomes;
    private List<Transfer> transfersIn;
    private List<Transfer> transfersOut;
    private List<OwnTransfer> ownTransfersIn;
    private List<OwnTransfer> ownTransfersOut;
    private Double totalIncome;
    private Double totalOutcome;
    private Double totalTransferIn;
    private Double totalTransferOut;
    private Double totalOwnTransferIn;
    private Double totalOwnTransferOut;
    private Double finalTotalIncome;
    private Double finalTotalOutcome;
    private Double balance;

    public Statements(Long accountId, List<Incomes> incomes, List<Outcomes> outcomes,
                      List<Transfer> transfersIn, List<Transfer> transfersOut,
                      List<OwnTransfer> ownTransfersIn, List<OwnTransfer> ownTransfersOut,
                      Double totalIncome, Double totalOutcome, Double totalTransferIn,
                      Double totalTransferOut, Double totalOwnTransferIn, Double totalOwnTransferOut,
                      Double income, Double outcome, Double balance) {
        this.accountId = accountId;
        this.incomes = incomes;
        this.outcomes = outcomes;
        this.transfersIn = transfersIn;
        this.transfersOut = transfersOut;
        this.ownTransfersIn = ownTransfersIn;
        this.ownTransfersOut = ownTransfersOut;
        this.totalIncome = totalIncome;
        this.totalOutcome = totalOutcome;
        this.totalTransferIn = totalTransferIn;
        this.totalTransferOut = totalTransferOut;
        this.totalOwnTransferIn = totalOwnTransferIn;
        this.totalOwnTransferOut = totalOwnTransferOut;
        this.finalTotalIncome = income;
        this.finalTotalOutcome = outcome;
        this.balance = balance;
    }
}