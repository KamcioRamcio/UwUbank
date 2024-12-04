package com.uwubank.uwubank.cards;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cards")
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public Card createCard(@RequestBody Card card) {
        return cardService.createCard(card);
    }

    @PostMapping("/activate/{cardId}")
    public Card activateCard(@PathVariable Long cardId, Card card) {
        return cardService.activateCard(card, cardId);
    }

    @PostMapping("/limits-change/{cardId}")
    public Card changeLimits(@PathVariable Long cardId, @RequestBody Map<String, Double> request) {
        double limits = request.get("limits");
        return cardService.changeLimits(cardId, limits);
    }

    @GetMapping("/customer/{customerId}")
    public List<Card> getCardsByCustomerId(@PathVariable Long customerId) {
        return cardService.getCardsByCustomerId(customerId);
    }
}