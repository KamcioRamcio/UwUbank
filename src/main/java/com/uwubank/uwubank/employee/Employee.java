package com.uwubank.uwubank.employee;

import com.uwubank.uwubank.branch.Branch;
import com.uwubank.uwubank.customer.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Data
@NoArgsConstructor
@Table("employees")
public class Employee {
    @Id
    @Column("employee_id")
    private Long employeeId;
    private String name;
    private Position position;
    @Column("branch_id")
    private Long branchId;
    @Transient
    private Branch branch;

    public Employee(String name, Position position, Long branchId) {
        this.name = name;
        this.position = position;
        this.branchId = branchId;
    }

}