package com.uwubank.uwubank.statement;

import com.uwubank.uwubank.transactions.Incomes;
import com.uwubank.uwubank.transactions.IncomesRepository;
import com.uwubank.uwubank.transactions.Outcomes;
import com.uwubank.uwubank.transactions.OutcomesRepository;
import com.uwubank.uwubank.transfer.OwnTransfer;
import com.uwubank.uwubank.transfer.OwnTransferRepository;
import com.uwubank.uwubank.transfer.Transfer;
import com.uwubank.uwubank.transfer.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatementsService {

    private final IncomesRepository incomesRepository;
    private final OutcomesRepository outcomesRepository;
    private final TransferRepository transferRepository;
    private final OwnTransferRepository ownTransferRepository;

    @Autowired
    public StatementsService(IncomesRepository incomesRepository, OutcomesRepository outcomesRepository, TransferRepository transferRepository, OwnTransferRepository ownTransferRepository) {
        this.incomesRepository = incomesRepository;
        this.outcomesRepository = outcomesRepository;
        this.transferRepository = transferRepository;
        this.ownTransferRepository = ownTransferRepository;
    }

    public Statements getStatements(Long accountId) {
        List<Incomes> incomes = incomesRepository.findAllByAccountId(accountId);
        List<Outcomes> outcomes = outcomesRepository.findAllByAccountId(accountId);
        List<Transfer> transfersIn = transferRepository.findAllByReceiverAccountId(accountId);
        List<Transfer> transfersOut = transferRepository.findAllBySenderAccountId(accountId);
        List<OwnTransfer> ownTransfersIn = ownTransferRepository.findAllByToAccountId(accountId);
        List<OwnTransfer> ownTransfersOut = ownTransferRepository.findAllByFromAccountId(accountId);

        Double totalIncome = calculateTotalIncome(incomes);
        Double totalOutcome = calculateTotalOutcome(outcomes);
        Double totalTransferIn = calculateTotalTransfer(transfersIn);
        Double totalTransferOut = calculateTotalTransfersOut(transfersOut);
        Double totalOwnTransferIn = calculateTotalOwnTransfer(ownTransfersIn);
        Double totalOwnTransferOut = calculateTotalOwnTransfersOut(ownTransfersOut);

        Double finalTotalIncome = totalIncome + totalTransferIn + totalOwnTransferIn;
        Double finalTotalOutcome = totalOutcome + totalTransferOut + totalOwnTransferOut;
        Double balance = finalTotalIncome - finalTotalOutcome;

        return new Statements(accountId, incomes, outcomes, transfersIn, transfersOut, ownTransfersIn, ownTransfersOut, totalIncome, totalOutcome, totalTransferIn, totalTransferOut, totalOwnTransferIn, totalOwnTransferOut, finalTotalIncome, finalTotalOutcome, balance);

    }

    private Double calculateTotalIncome(List<Incomes> incomes) {
        return incomes.stream().mapToDouble(Incomes::getAmount).sum();
    }

    private Double calculateTotalTransfer(List<Transfer> transfersIn) {
        return transfersIn.stream().mapToDouble(Transfer::getAmount).sum();
    }

    private Double calculateTotalOwnTransfer(List<OwnTransfer> ownTransfersIn) {
        return ownTransfersIn.stream().mapToDouble(OwnTransfer::getAmount).sum();
    }

    private Double calculateTotalOutcome(List<Outcomes> outcomes) {
        return outcomes.stream().mapToDouble(Outcomes::getAmount).sum();
    }

    private Double calculateTotalTransfersOut(List<Transfer> transfersOut) {
        return transfersOut.stream().mapToDouble(Transfer::getAmount).sum();
    }

    private Double calculateTotalOwnTransfersOut(List<OwnTransfer> ownTransfersOut) {
        return ownTransfersOut.stream().mapToDouble(OwnTransfer::getAmount).sum();
    }
}