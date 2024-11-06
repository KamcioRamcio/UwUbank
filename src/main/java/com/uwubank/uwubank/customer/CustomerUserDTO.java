package com.uwubank.uwubank.customer;

import com.uwubank.uwubank.users.User;
import lombok.Data;

@Data
public class CustomerUserDTO {
    private Customer customer;
    private User user;
}