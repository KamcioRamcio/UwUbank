package com.uwubank.uwubank.transfer;

import com.uwubank.uwubank.account.Account;
import com.uwubank.uwubank.account.AccountRepository;
import com.uwubank.uwubank.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service

public class TransferService {

    private final TransferRepository transferRepository;
    private final OwnTransferRepository ownTransferRepository;
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final ExchangeService exchangeService;

    @Autowired
    public TransferService(TransferRepository transferRepository, OwnTransferRepository ownTransferRepository, CustomerRepository customerRepository, AccountRepository accountRepository, ExchangeService exchangeService) {
        this.transferRepository = transferRepository;
        this.ownTransferRepository = ownTransferRepository;
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.exchangeService = exchangeService;
    }

    @Transactional
    public Transfer executeTransfer(Transfer transfer) {
        Account senderAccount = accountRepository.findById(transfer.getSenderAccountId()).orElseThrow(() -> new IllegalArgumentException("Invalid sender account ID"));
        Account receiverAccount = accountRepository.findById(transfer.getReceiverAccountId()).orElseThrow(() -> new IllegalArgumentException("Invalid receiver account ID"));

        if (senderAccount.getBalance() < transfer.getAmount()) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        if (receiverAccount.getCustomerId().equals(transfer.getSenderAccountId())) {
            throw new IllegalArgumentException("Account belong to customer");
        }

        double exchangeRate = 1.0;
        if (!senderAccount.getAccountType().equals(receiverAccount.getAccountType())) {
            exchangeRate = exchangeService.getExchangeRate(senderAccount.getAccountType().toString(), receiverAccount.getAccountType().toString());
        }

        double convertedAmount = transfer.getAmount() * exchangeRate;

        senderAccount.setBalance(senderAccount.getBalance() - transfer.getAmount());
        accountRepository.save(senderAccount);

        receiverAccount.setBalance(receiverAccount.getBalance() + convertedAmount);
        accountRepository.save(receiverAccount);

        transfer.setDate(new Date());
        return transferRepository.save(transfer);


    }

    @Transactional
    public OwnTransfer executeOwnTransfer(OwnTransfer ownTransfer) {
        Account fromAccount = accountRepository.findById(ownTransfer.getFromAccountId()).orElseThrow(() -> new IllegalArgumentException("Invalid 'from' account ID"));
        Account toAccount = accountRepository.findById(ownTransfer.getToAccountId()).orElseThrow(() -> new IllegalArgumentException("Invalid 'to' account ID"));

        if (!fromAccount.getCustomerId().equals(ownTransfer.getCustomerId())) {
            throw new IllegalArgumentException("Account does not belong to customer");
        }

        if (!toAccount.getCustomerId().equals(ownTransfer.getCustomerId())) {
            throw new IllegalArgumentException("Account does not belong to customer");
        }
        if (fromAccount.getBalance() < ownTransfer.getAmount()) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        double exchangeRate = 1.0;
        if (!fromAccount.getAccountType().equals(toAccount.getAccountType())) {
            exchangeRate = exchangeService.getExchangeRate(fromAccount.getAccountType().toString(), toAccount.getAccountType().toString());
        }

        double convertedAmount = ownTransfer.getAmount() * exchangeRate;

        fromAccount.setBalance(fromAccount.getBalance() - ownTransfer.getAmount());
        accountRepository.save(fromAccount);

        toAccount.setBalance(toAccount.getBalance() + convertedAmount);
        accountRepository.save(toAccount);

        ownTransfer.setDate(new Date());
        return ownTransferRepository.save(ownTransfer);
    }

    public List<TransferDetailsDTO> getTransferDetailsByCustomerId(Long customerId) {
        return transferRepository.findTransferDetailsByCustomerId(customerId);
    }

    public List<Transfer> getAllTransfersByCustomerId(Long customerId) {
        return transferRepository.findAllByCustomerId(customerId);
    }

    public List<OwnTransfer> getAllOwnTransfersByCustomerId(Long customerId) {
        return ownTransferRepository.findAllByCustomerId(customerId);
    }
}