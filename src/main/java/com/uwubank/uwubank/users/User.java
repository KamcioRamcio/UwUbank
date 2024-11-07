package com.uwubank.uwubank.users;

import com.uwubank.uwubank.customer.Customer;
import com.uwubank.uwubank.employee.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@Table("users")
public class User {

    @Id
    @Column("user_id")
    private Long userId;
    private String username;
    private String password;
    private String role;

    @Column("customer_id")
    private Long customerId;

    @Column("employee_id")
    private Long employeeId;

    @Transient
    private Customer customer;
    @Transient
    private Employee employee;


    public User(String username, String password, String role, Customer customer) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.customerId = customer.getCustomerId();
    }

    public User(String username, String password, String role, Employee employee) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.employeeId = employee.getEmployeeId();
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


}