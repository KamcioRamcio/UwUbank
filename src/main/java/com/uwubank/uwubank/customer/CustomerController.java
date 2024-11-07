package com.uwubank.uwubank.customer;

import com.uwubank.uwubank.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public Customer createCustomer(@RequestBody CustomerUserDTO customerUserDTO) {
        Customer customer = customerUserDTO.getCustomer();
        User user = customerUserDTO.getUser();
        return customerService.createCustomer(customer, user);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

}