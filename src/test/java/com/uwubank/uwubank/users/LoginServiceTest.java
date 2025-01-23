package com.uwubank.uwubank.users;

import com.uwubank.uwubank.account.AccountRepository;
import com.uwubank.uwubank.branch.BranchService;
import com.uwubank.uwubank.customer.Customer;
import com.uwubank.uwubank.customer.CustomerRepository;
import com.uwubank.uwubank.employee.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

    @Mock private UserRepository userRepository;
    @Mock private CustomerRepository customerRepository;
    @Mock private EmployeeRepository employeeRepository;
    @Mock private AccountRepository accountRepository;
    @Mock private BranchService branchService;

    @InjectMocks private LoginService loginService;

    @Test
    void loginCustomer_ValidCredentials_ReturnsDTO() {
        // Arrange
        String username = "validUser";
        String password = "validPass";

        User user = new User(username, password);
        user.setCustomerId(1L);

        Customer customer = new Customer();
        customer.setCustomerId(1L);

        // Use exact argument matching
        given(userRepository.findByUsername(username)).willReturn(Optional.of(user));
        given(customerRepository.findById(user.getCustomerId())).willReturn(Optional.of(customer));
        given(accountRepository.findByCustomerId(user.getCustomerId())).willReturn(Collections.emptyList());

        // Act
        CustomerAccountDTO result = loginService.loginCustomer(username, password);

        // Assert
        assertThat(result.getCustomer()).isEqualTo(customer);
        assertThat(result.getAccounts()).isEmpty();
    }

    @Test
    void loginCustomer_InvalidUsername_ThrowsException() {
        String invalidUsername = "nonExistentUser";

        given(userRepository.findByUsername(invalidUsername)).willReturn(Optional.empty());

        assertThatThrownBy(() -> loginService.loginCustomer(invalidUsername, "anyPassword"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid username or password");
    }
}