package com.uwubank.uwubank.transactions;


import org.springframework.web.bind.annotation.*;

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
}
