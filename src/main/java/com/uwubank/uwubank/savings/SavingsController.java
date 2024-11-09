package com.uwubank.uwubank.savings;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/savings")
public class SavingsController {
    private final SavingsService savingsService;

    public SavingsController(SavingsService savingsService) {
        this.savingsService = savingsService;
    }

    @PostMapping
    public Savings createSavings(@RequestBody Savings savings) {
        return savingsService.createSavings(savings);
    }
}
