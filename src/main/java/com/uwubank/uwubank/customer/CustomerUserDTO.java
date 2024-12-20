package com.uwubank.uwubank.customer;

import com.uwubank.uwubank.account.Account;
import com.uwubank.uwubank.users.User;
import lombok.Data;

import java.util.List;

@Data
public class CustomerUserDTO {
    private Customer customer;
    private User user;
    private List<Account> accounts;
}