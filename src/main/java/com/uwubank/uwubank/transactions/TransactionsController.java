package com.uwubank.uwubank.transactions;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionsController {

    private final TransactionsService transactionsService;

    public TransactionsController(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @PostMapping("/incomes")
    public Incomes createIncome(@RequestBody Incomes income) {
        return transactionsService.executeIncome(income);
    }

    @PostMapping("/outcomes")
    public Outcomes createOutcome(@RequestBody Outcomes outcome) {
        return transactionsService.executeOutcome(outcome);
    }

    @GetMapping("/incomes/{customerId}")
    public List<Incomes> getIncomesByCustomerId (@PathVariable Long customerId) {
        return transactionsService.getIncomesByCustomerId(customerId);
    }

    @GetMapping("/outcomes/{customerId}")
    public List<Outcomes> getOutcomesByCustomerId (@PathVariable Long customerId) {
        return transactionsService.getOutcomesByCustomerId(customerId);
    }
}
