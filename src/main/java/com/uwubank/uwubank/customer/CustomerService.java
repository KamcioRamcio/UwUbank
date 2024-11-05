package com.uwubank.uwubank.customer;

import com.uwubank.uwubank.users.User;
import com.uwubank.uwubank.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    public Customer createCustomer(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        User user = new User(customer.getEmail(), "defaultPassword", "CUSTOMER", savedCustomer);
        userRepository.save(user);
        return savedCustomer;
    }

    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElse(null);
    }
}