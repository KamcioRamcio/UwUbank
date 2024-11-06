package com.uwubank.uwubank.employee;

import com.uwubank.uwubank.users.User;
import lombok.Data;

@Data
public class EmployeeUserDTO {
    private Employee employee;
    private User user;
}
