package com.uwubank.uwubank.transfer;

import com.uwubank.uwubank.account.Account;
import com.uwubank.uwubank.account.AccountRepository;
import com.uwubank.uwubank.account.AccountType;
import com.uwubank.uwubank.customer.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TransferServiceTest {

    @Mock private TransferRepository transferRepository;
    @Mock private OwnTransferRepository ownTransferRepository;
    @Mock private CustomerRepository customerRepository;
    @Mock private AccountRepository accountRepository;
    @Mock private ExchangeService exchangeService;

    @InjectMocks private TransferService transferService;

    @Test
    void executeTransfer_ValidTransfer_UpdatesBalances() {
        // Arrange
        Account sender = new Account(1L, AccountType.USD);
        sender.setBalance(100.0);
        Account receiver = new Account(2L, AccountType.EURO);
        receiver.setBalance(50.0);

        Transfer transfer = new Transfer(1L, 1L, 2L, 50.0, new Date());

        given(accountRepository.findById(1L)).willReturn(Optional.of(sender));
        given(accountRepository.findById(2L)).willReturn(Optional.of(receiver));

        // Mock the exchange rate for USD to EURO
        given(exchangeService.getExchangeRate("USD", "EURO")).willReturn(0.85);

        given(transferRepository.save(any())).willReturn(transfer);

        // Act
        Transfer result = transferService.executeTransfer(transfer);

        // Assert
        assertThat(sender.getBalance()).isEqualTo(50.0); // 100 - 50
        assertThat(receiver.getBalance()).isEqualTo(92.5); // 50 + (50 * 0.85)
    }

    @Test
    void executeOwnTransfer_ValidTransfer_UpdatesBalances() {
        // Arrange
        // Both accounts belong to customer 1L
        Account fromAccount = new Account(1L, AccountType.USD);
        fromAccount.setCustomerId(1L);
        fromAccount.setBalance(200.0);

        Account toAccount = new Account(2L, AccountType.EURO);
        toAccount.setCustomerId(1L); // Same customer
        toAccount.setBalance(100.0);

        OwnTransfer ownTransfer = new OwnTransfer(1L, 100.0, 1L, 2L, new Date());

        given(accountRepository.findById(1L)).willReturn(Optional.of(fromAccount));
        given(accountRepository.findById(2L)).willReturn(Optional.of(toAccount));

        // Mock the exchange rate for USD to EURO
        given(exchangeService.getExchangeRate("USD", "EURO")).willReturn(0.85);

        given(ownTransferRepository.save(any())).willReturn(ownTransfer);

        // Act
        OwnTransfer result = transferService.executeOwnTransfer(ownTransfer);

        // Assert
        assertThat(fromAccount.getBalance()).isEqualTo(100.0); // 200 - 100
        assertThat(toAccount.getBalance()).isEqualTo(185.0); // 100 + (100 * 0.85)
    }
}