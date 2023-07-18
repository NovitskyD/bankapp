package com.practice.bank.helpers;

import com.practice.bank.repository.CardRepository;


public class CardNumberGenerator implements NumberGenerator{
    private static final int CARD_NUMBER_LENGTH = 12;
    private final CardRepository cardRepository;

    public CardNumberGenerator(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public String generateNumber() {
        String cardNumber;

        do {
            cardNumber = RandomNumberGenerator.generateNumber(CARD_NUMBER_LENGTH);
        }
        while (!isCardNumberUnique(cardNumber));

        return cardNumber;
    }

    private boolean isCardNumberUnique(String cardNumber) {
        return !cardRepository.existsByCardNumber(cardNumber);
    }
}
