package com.uwubank.uwubank.transactions;

import com.uwubank.uwubank.account.Account;
import com.uwubank.uwubank.account.AccountRepository;
import com.uwubank.uwubank.account.AccountType;
import com.uwubank.uwubank.transfer.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionsService {

    private final IncomesRepository incomesRepository;
    private final OutcomesRepository outcomesRepository;
    private final ExchangeService exchangeService;
    private final AccountRepository accountRepository;

    @Autowired
    public TransactionsService(IncomesRepository incomesRepository, OutcomesRepository outcomesRepository, ExchangeService exchangeService, AccountRepository accountRepository) {
        this.incomesRepository = incomesRepository;
        this.outcomesRepository = outcomesRepository;
        this.exchangeService = exchangeService;
        this.accountRepository = accountRepository;
    }

    private Account getAccountById(Long accountId, boolean isIncome) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid " + (isIncome ? "income" : "outcome") + " account ID"));
    }

    private double calculateConvertedAmount(Account customerAccount, String currency, double amount) {
        AccountType accountCurrency;
        try {
            accountCurrency = AccountType.valueOf(currency);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid currency type");
        }

        double exchangeRate = 1.0;
        if (!customerAccount.getAccountType().equals(accountCurrency)) {
            exchangeRate = exchangeService.getExchangeRate(customerAccount.getAccountType().toString(), accountCurrency.toString());
        }

        return amount * exchangeRate;
    }

    public boolean validCustomerAccount(Long accountId, Long customerId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Not customer account"));
        return account.getCustomerId().equals(customerId);
    }



    @Transactional
    public Incomes executeIncome(Incomes income) {
        Account customerAccount = getAccountById(income.getAccountId(), true);
        if (!validCustomerAccount(income.getAccountId(), income.getCustomerId())) {
            throw new IllegalArgumentException("Not customer account");
        }
        double convertedAmount = calculateConvertedAmount(customerAccount, String.valueOf(income.getCurrency()), income.getAmount());

        customerAccount.setBalance(customerAccount.getBalance() + convertedAmount);
        accountRepository.save(customerAccount);

        income.setDate(java.time.LocalDate.now());
        return incomesRepository.save(income);
    }

    @Transactional
    public Outcomes executeOutcome(Outcomes outcome) {
        Account customerAccount = getAccountById(outcome.getAccountId(), false);
        if (!validCustomerAccount(outcome.getAccountId(), outcome.getCustomerId())) {
            throw new IllegalArgumentException("Not customer account");
        }
        double convertedAmount = calculateConvertedAmount(customerAccount, String.valueOf(outcome.getCurrency()), outcome.getAmount());

        if (customerAccount.getBalance() < convertedAmount) {
            throw new IllegalArgumentException("Not enough funds");
        }

        customerAccount.setBalance(customerAccount.getBalance() - convertedAmount);
        accountRepository.save(customerAccount);

        outcome.setDate(java.time.LocalDate.now());
        return outcomesRepository.save(outcome);
    }

    public List<Incomes> getIncomesByCustomerId(Long customerId) {
        return incomesRepository.findAllByCustomerId(customerId);
    }

    public List<Outcomes> getOutcomesByCustomerId(Long customerId) {
        return outcomesRepository.findAllByCustomerId(customerId);
    }
}