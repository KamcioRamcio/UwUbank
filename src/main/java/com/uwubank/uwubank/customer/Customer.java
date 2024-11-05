package com.uwubank.uwubank.customer;
import com.uwubank.uwubank.users.User;
import com.uwubank.uwubank.account.Account;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Data
@NoArgsConstructor
@Table(name = "customers")
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
    private List<Account> accounts;

    private User user;

    public Customer(String name, String surname, String pesel, String address, String phoneNumber, String email) {

        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;

        this.user = new User(email, "defaultPassword", "CUSTOMER", this);
    }
    public void addAccount(Account account) {
        accounts.add(account);
    }
    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", pesel='" + pesel + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", accounts=" + accounts +
                ", user=" + user +
                '}';
    }
}
