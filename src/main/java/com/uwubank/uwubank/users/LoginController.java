package com.uwubank.uwubank.users;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/customer")
    public CustomerAccountDTO loginUser(@RequestBody LoginRequest loginRequest) {
        return loginService.loginCustomer(loginRequest.getUsername(), loginRequest.getPassword());
    }

    @PostMapping("/employee")
    public EmployeeCustomersDTO loginEmployee(@RequestBody LoginRequest loginRequest) {
        return loginService.loginEmployee(loginRequest.getUsername(), loginRequest.getPassword());
    }
}