package com.uwubank.uwubank.employee;

import com.uwubank.uwubank.branch.BranchRepository;
import com.uwubank.uwubank.customer.Customer;
import com.uwubank.uwubank.customer.CustomerRepository;
import com.uwubank.uwubank.users.User;
import com.uwubank.uwubank.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final BranchRepository branchRepository;
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, BranchRepository branchRepository, UserRepository userRepository, CustomerRepository customerRepository) {
        this.employeeRepository = employeeRepository;
        this.branchRepository = branchRepository;
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
    }

    public Employee createEmployee(Employee employee, User user) {
        if (branchRepository.findById(employee.getBranchId()).isPresent()) {
            Employee savedEmployee = employeeRepository.save(employee);
            User _user = new User(user.getUsername(), user.getPassword(), "EMPLOYEE", employee);
            userRepository.save(_user);
            return savedEmployee;
        } else {
            throw new IllegalArgumentException("Invalid branch ID");
        }
    }

    public List<Customer> getCustomersForEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee ID"));
        return customerRepository.findByBranchId(employee.getBranchId());
    }

    public List<Employee> getAllEmployees() {
        return (List<Employee>) employeeRepository.findAll();
    }
}