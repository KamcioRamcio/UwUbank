package com.uwubank.uwubank.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.uwubank.uwubank.BasicController;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController extends BasicController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @GetMapping("/{customerId}")
    public List<Account> getAccountsByCustomerId(@PathVariable Long customerId) {
        return accountService.getAccountsByCustomerId(customerId);
    }

    @PutMapping("/up/{accountId}")
    public Account updateBalanceUp(@PathVariable Long accountId, @RequestBody Map<String, Double> request) {
        double amount = request.get("amount");
        return accountService.updateBalanceUp(accountId, amount);
    }

    @PutMapping("/down/{accountId}")
    public Account updateBalanceDown(@PathVariable Long accountId, @RequestBody Map<String, Double> request) {
        double amount = request.get("amount");
        return accountService.updateBalanceDown(accountId, amount);
    }


}