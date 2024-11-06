package com.uwubank.uwubank.account;

import com.uwubank.uwubank.customer.Customer;
import com.uwubank.uwubank.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    public Account createAccount(Account account) {
        Optional<Customer> customerOptional = customerRepository.findById(account.getCustomerId());
        if (customerOptional.isPresent()) {
            return accountRepository.save(account);
        } else {
            throw new IllegalArgumentException("Invalid customer ID");
        }
    }

    public List<Account> getAccountsByCustomerId(Long customerId) {
        return accountRepository.findByCustomerId(customerId);
    }

    public Account updateBalanceUp(Long accountId, double amount) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            account.setBalance(account.getBalance() + amount);
            return accountRepository.save(account);
        } else {
            throw new IllegalArgumentException("Invalid account ID");
        }
    }

    public Account updateBalanceDown(Long accountId, double amount) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            account.setBalance(account.getBalance() - amount);
            return accountRepository.save(account);
        } else {
            throw new IllegalArgumentException("Invalid account ID");
        }
    }


}