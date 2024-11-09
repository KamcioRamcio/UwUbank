package com.uwubank.uwubank.savings;


import com.uwubank.uwubank.customer.Customer;
import com.uwubank.uwubank.customer.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SavingsService {
    private final SavingsRepository savingsRepository;
    private final CustomerRepository customerRepository;

    public SavingsService(SavingsRepository savingsRepository, CustomerRepository customerRepository) {
        this.savingsRepository = savingsRepository;
        this.customerRepository = customerRepository;
    }

    public Savings createSavings(Savings savings) {
        Optional<Customer> customerOptional = customerRepository.findById(savings.getCustomerId());
        if (customerOptional.isPresent()) {
            return savingsRepository.save(savings);
        } else {
            throw new IllegalArgumentException("Invalid customer ID");
        }
    }


}
