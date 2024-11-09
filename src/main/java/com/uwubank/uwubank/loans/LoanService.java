package com.uwubank.uwubank.loans;


import com.uwubank.uwubank.customer.Customer;
import com.uwubank.uwubank.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository, CustomerRepository customerRepository) {
        this.loanRepository = loanRepository;
        this.customerRepository = customerRepository;
    }

    public Loan createLoan(Loan loan) {
        Optional<Customer> customerOptional = customerRepository.findById(loan.getCustomerId());
        if (customerOptional.isPresent()) {
            return loanRepository.save(loan);
        } else {
            throw new IllegalArgumentException("Invalid customer ID");
        }
    }

    public Loan updateApproval(Long loanId, boolean approved) {
        Optional<Loan> loanOptional = loanRepository.findById(loanId);
        if (loanOptional.isPresent()) {
            Loan loan = loanOptional.get();
            loan.setApproved(approved);
            return loanRepository.save(loan);
        } else {
            throw new IllegalArgumentException("Invalid loan ID");
        }
    }
}

