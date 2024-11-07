package com.uwubank.uwubank.users;

import com.uwubank.uwubank.branch.Branch;
import com.uwubank.uwubank.branch.BranchService;
import com.uwubank.uwubank.customer.CustomerRepository;
import com.uwubank.uwubank.account.AccountRepository;
import com.uwubank.uwubank.employee.Employee;
import com.uwubank.uwubank.employee.EmployeeRepository;
import com.uwubank.uwubank.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class LoginService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final AccountRepository accountRepository;
    private final BranchService branchService;

    @Autowired
    public LoginService(UserRepository userRepository, CustomerRepository customerRepository, EmployeeRepository employeeRepository, AccountRepository accountRepository, BranchService branchService) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.accountRepository = accountRepository;
        this.branchService = branchService;
    }

    public CustomerAccountDTO loginCustomer(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        User user = optionalUser.get();

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        CustomerAccountDTO customerAccountDTO = new CustomerAccountDTO();

        if (user.getCustomerId() != null) {
            Customer customer = customerRepository.findById(user.getCustomerId()).orElseThrow(() -> new IllegalArgumentException("Customer not found"));
            customerAccountDTO.setCustomer(customer);
            customerAccountDTO.setAccounts(accountRepository.findByCustomerId(customer.getCustomerId()));
        } else if (user.getEmployeeId() != null) {
            user.setEmployee(employeeRepository.findById(user.getEmployeeId()).orElseThrow(() -> new IllegalArgumentException("Employee not found")));
        }

        return customerAccountDTO;
    }


    public EmployeeCustomersDTO loginEmployee(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        User user = optionalUser.get();

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        EmployeeCustomersDTO employeeCustomersDTO = new EmployeeCustomersDTO();

        if (user.getEmployeeId() != null) {
            Employee employee = employeeRepository.findById(user.getEmployeeId())
                    .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
            Branch branch = branchService.getBranchWithDetails(employee.getBranchId())
                    .orElseThrow(() -> new IllegalArgumentException("Branch not found"));

            employeeCustomersDTO.setEmployee(employee);
            employeeCustomersDTO.setCustomers(customerRepository.findByBranchId(employee.getBranchId()));
            employee.setBranch(branch);
        }

        return employeeCustomersDTO;
    }
}