package com.uwubank.uwubank.users;

import com.uwubank.uwubank.customer.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

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

    public User(String username, String password, String role, Customer customer) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.customerId = customer.getCustomerId();
    }
}