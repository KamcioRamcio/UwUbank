package com.uwubank.uwubank.account;

import com.uwubank.uwubank.customer.Customer;
import com.uwubank.uwubank.customer.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock private AccountRepository accountRepository;
    @Mock private CustomerRepository customerRepository;

    @InjectMocks private AccountService accountService;

    @Test
    void createAccount_ValidCustomer_CreatesAccount() {
        // Arrange
        Account account = new Account(1L, AccountType.EURO); // Use correct constructor
        Customer customer = new Customer();
        customer.setCustomerId(1L);

        given(customerRepository.findById(1L)).willReturn(Optional.of(customer));
        given(accountRepository.save(any(Account.class))).willReturn(account);

        // Act
        Account result = accountService.createAccount(account);

        // Assert
        assertThat(result).isEqualTo(account);
        verify(accountRepository).save(account);
    }

    @Test
    void updateBalanceUp_ValidAccount_UpdatesBalance() {
        // Arrange
        Account account = new Account(1L, AccountType.USD);
        account.setBalance(100.0); // Explicitly set initial balance

        given(accountRepository.findById(1L)).willReturn(Optional.of(account));
        given(accountRepository.save(any(Account.class))).willReturn(account);

        // Act
        Account result = accountService.updateBalanceUp(1L, 50.0);

        // Assert
        assertThat(result.getBalance()).isEqualTo(150.0);
    }

    @Test
    void updateBalanceUp_InvalidAccount_ThrowsException() {
        given(accountRepository.findById(999L)).willReturn(Optional.empty());

        assertThatThrownBy(() -> accountService.updateBalanceUp(999L, 50.0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid account ID");
    }
}