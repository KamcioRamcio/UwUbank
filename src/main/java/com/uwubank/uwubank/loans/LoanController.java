package com.uwubank.uwubank.loans;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/loans")
public class LoanController {
    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public Loan createLoan(@RequestBody Loan loan) {
        return loanService.createLoan(loan);
    }

    @PostMapping("/approve")
    public Loan approveLoan(Long loanId) {
        return loanService.updateApproval(loanId, true);
    }
}
