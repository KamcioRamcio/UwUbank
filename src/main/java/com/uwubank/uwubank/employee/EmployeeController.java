package com.uwubank.uwubank.employee;


import com.uwubank.uwubank.customer.Customer;
import com.uwubank.uwubank.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.uwubank.uwubank.BasicController;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController extends BasicController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public Employee createEmployee(@RequestBody EmployeeUserDTO employeeUserDTO) {
        Employee employee = employeeUserDTO.getEmployee();
        User user = employeeUserDTO.getUser();
        return employeeService.createEmployee(employee, user);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{employeeId}/customers")
    public List<Customer> getCustomersForEmployee(@PathVariable Long employeeId) {
        return employeeService.getCustomersForEmployee(employeeId);
    }
}
