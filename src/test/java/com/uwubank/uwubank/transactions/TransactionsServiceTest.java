package com.uwubank.uwubank.transactions;

import com.uwubank.uwubank.account.Account;
import com.uwubank.uwubank.account.AccountRepository;
import com.uwubank.uwubank.account.AccountType;
import com.uwubank.uwubank.transfer.ExchangeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TransactionsServiceTest {

    @Mock private IncomesRepository incomesRepository;
    @Mock private OutcomesRepository outcomesRepository;
    @Mock private ExchangeService exchangeService;
    @Mock private AccountRepository accountRepository;

    @InjectMocks private TransactionsService transactionsService;

    @Test
    void executeIncome_ValidTransaction_UpdatesBalance() {
        // 1. Fix Account creation
        Account account = new Account(1L, AccountType.USD);
        account.setBalance(100.0);
        Incomes income = new Incomes(1L, 50.0, Currencies.USD, 1L, LocalDate.now());


        given(accountRepository.findById(1L)).willReturn(Optional.of(account));
        given(incomesRepository.save(any())).willReturn(income);

        Incomes result = transactionsService.executeIncome(income);

        assertThat(account.getBalance()).isEqualTo(150.0);
        assertThat(result.getDate()).isEqualTo(LocalDate.now());
    }

    @Test
    void executeOutcome_InsufficientFunds_ThrowsException() {
        // 1. Fix Account creation
        Account account = new Account(1L, AccountType.EURO); // Use enum
        account.setBalance(30.0); // Set balance explicitly

        // 2. Correct Outcomes constructor
        Outcomes outcome = new Outcomes(
                1L,                   // customerId
                50.0,                 // amount
                Currencies.USD,       // currency
                1L,                   // accountId
                LocalDate.now()       // date
        );

        given(accountRepository.findById(1L)).willReturn(Optional.of(account));
        given(exchangeService.getExchangeRate(any(), any())).willReturn(1.0);

        assertThatThrownBy(() -> transactionsService.executeOutcome(outcome))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Not enough funds");
    }
}