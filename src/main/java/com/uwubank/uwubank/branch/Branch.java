package com.uwubank.uwubank.branch;

import com.uwubank.uwubank.customer.Customer;
import com.uwubank.uwubank.employee.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.annotation.Transient;

import java.util.List;

@Data
@NoArgsConstructor
@Table("branches")
public class Branch {
    @Id
    @Column("branch_id")
    private Long branchId;
    private String branchName;
    private String branchAddress;
    private String branchPhoneNumber;
    @Transient
    private List<Customer> customers;
    @Transient
    private List<Employee> employees;

    public Branch(String branchName, String branchAddress, String branchPhoneNumber) {
        this.branchName = branchName;
        this.branchAddress = branchAddress;
        this.branchPhoneNumber = branchPhoneNumber;
    }


}