package com.uwubank.uwubank.users;

import com.uwubank.uwubank.account.Account;
import com.uwubank.uwubank.customer.Customer;
import lombok.Data;

import java.util.List;

@Data
public class CustomerAccountDTO {
    private Customer customer;
    private List<Account> accounts;
}