package com.uwubank.uwubank.customer;

import com.uwubank.uwubank.branch.Branch;
import com.uwubank.uwubank.branch.BranchRepository;
import com.uwubank.uwubank.users.User;
import com.uwubank.uwubank.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final BranchRepository branchRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, UserRepository userRepository, BranchRepository branchRepository) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
        this.branchRepository = branchRepository;
    }

    public Customer createCustomer(Customer customer, User user) {
        Optional<Branch> branchOptional = branchRepository.findById(customer.getBranchId());
        if (branchOptional.isPresent()) {
            customer.setBranchId(branchOptional.get().getBranchId());
        } else {
            throw new IllegalArgumentException("Invalid branch ID");
        }
        Customer savedCustomer = customerRepository.save(customer);
        User _user = new User(user.getUsername(), user.getPassword(), "CUSTOMER", savedCustomer);
        userRepository.save(_user);
        return savedCustomer;
    }

    public List<Customer> getAllCustomers() {
        return (List<Customer>) customerRepository.findAll();
    }
}