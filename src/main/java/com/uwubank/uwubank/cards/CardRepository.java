package com.uwubank.uwubank.cards;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardRepository extends CrudRepository<Card, Long> {
    List<Card> findByCardNumber(String cardNumber);
    List<Card> findByCustomerId(Long customerId);
    List<Card> findByCvv(String cvv);
}
