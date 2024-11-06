package com.uwubank.uwubank.branch;

import com.uwubank.uwubank.customer.CustomerRepository;
import com.uwubank.uwubank.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BranchService {

    private final BranchRepository branchRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public BranchService(BranchRepository branchRepository, CustomerRepository customerRepository, EmployeeRepository employeeRepository) {
        this.branchRepository = branchRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
    }

    public Optional<Branch> getBranchWithDetails(Long branchId) {
        Optional<Branch> branchOptional = branchRepository.findById(branchId);
        if (branchOptional.isPresent()) {
            Branch branch = branchOptional.get();
            branch.setCustomers(customerRepository.findByBranchId(branchId));
            branch.setEmployees(employeeRepository.findByBranchId(branchId));
            return Optional.of(branch);
        }
        return Optional.empty();
    }
}