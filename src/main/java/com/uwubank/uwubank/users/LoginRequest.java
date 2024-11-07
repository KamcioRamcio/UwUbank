package com.uwubank.uwubank.users;

import lombok.Data;


@Data
public class LoginRequest {
    private String username;
    private String password;

}