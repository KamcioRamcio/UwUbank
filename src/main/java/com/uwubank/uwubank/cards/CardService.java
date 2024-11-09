package com.uwubank.uwubank.cards;

import com.uwubank.uwubank.account.AccountRepository;
import com.uwubank.uwubank.customer.Customer;
import com.uwubank.uwubank.account.Account;
import com.uwubank.uwubank.customer.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    public CardService(CardRepository cardRepository, CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.cardRepository = cardRepository;
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

    public boolean isAccountOwnedByCustomer(Long accountId, Long customerId) {
        List<Account> accounts = accountRepository.findByCustomerId(customerId);
        return accounts.stream().anyMatch(account -> account.getAccountId().equals(accountId));
    }

    private String generateCardNumber() {
        Random random = new Random();
        String cardNumber;
        do {
            StringBuilder cardNumberBuilder = new StringBuilder();
            for (int i = 0; i < 16; i++) {
                cardNumberBuilder.append(random.nextInt(10));
            }
            cardNumber = cardNumberBuilder.toString();
        } while (!cardRepository.findByCardNumber(cardNumber).isEmpty());
        return cardNumber;
    }

    private String generateCVV() {
        Random random = new Random();
        String cvv;
        do {
            StringBuilder cvvBuilder = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                cvvBuilder.append(random.nextInt(10));
            }
            cvv = cvvBuilder.toString();
        } while (!cardRepository.findByCvv(cvv).isEmpty());
        return cvv;
    }

    private Date generateExpirationDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 3);
        return calendar.getTime();
    }

    public Card createCard(Card card) {
        Optional<Customer> customer = customerRepository.findById(card.getCustomerId());
        Optional<Account> account = accountRepository.findById(card.getAccountId());
        if (customer.isPresent() && account.isPresent() && isAccountOwnedByCustomer(card.getAccountId(), card.getCustomerId())) {
            card.setCustomer(customer.get());
            card.setAccount(account.get());
            card.setCardNumber(generateCardNumber());
            card.setCvv(generateCVV());
            card.setExpirationDate(generateExpirationDate());
            card.setStatus("toActive");
            return cardRepository.save(card);
        } else {
            throw new IllegalArgumentException("Invalid customer ID");
        }
    }

    public Card activateCard(Card card, Long cardId) {
        Optional<Card> cardOptional = cardRepository.findById(cardId);
        if (cardOptional.isPresent()) {
            Card cardToActivate = cardOptional.get();
            cardToActivate.setStatus("actived");
            return cardRepository.save(cardToActivate);
        } else {
            throw new IllegalArgumentException("Invalid card ID");
        }
    }

    public Card changeLimits(Long cardId, double newLimit) {
        Optional<Card> cardOptional = cardRepository.findById(cardId);
        if (cardOptional.isPresent()) {
            Card card = cardOptional.get();
            card.setLimits(newLimit);
            return cardRepository.save(card);
        } else {
            throw new IllegalArgumentException("Invalid card ID");
        }
    }
}