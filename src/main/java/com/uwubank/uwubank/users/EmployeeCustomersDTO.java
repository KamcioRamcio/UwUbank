package com.uwubank.uwubank.users;

import com.uwubank.uwubank.branch.Branch;
import com.uwubank.uwubank.customer.Customer;
import com.uwubank.uwubank.employee.Employee;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeCustomersDTO {
    private Employee employee;
    private List<Customer> customers;
}
