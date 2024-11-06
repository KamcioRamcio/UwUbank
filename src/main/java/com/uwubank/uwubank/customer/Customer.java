package com.uwubank.uwubank.customer;

import com.uwubank.uwubank.account.Account;
import com.uwubank.uwubank.branch.Branch;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Table("customers")
public class Customer {
    @Id
    @Column("customer_id")
    private Long customerId;
    private String name;
    private String surname;
    private String pesel;
    private String address;
    private String phoneNumber;
    private String email;
    @Column("branch_id")
    private Long branchId;

    public Customer(String name, String surname, String pesel, String address, String phoneNumber, String email, Long branchId) {
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.branchId = branchId;
    }


}