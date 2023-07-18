package com.practice.bank.helpers;

import com.practice.bank.repository.CardRepository;

public class CVVGenerator implements NumberGenerator{
    private static final int CVV_LENGTH = 3;
    private final CardRepository cardRepository;

    public CVVGenerator(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public String generateNumber() {
        String cvv;

        do {
            cvv = RandomNumberGenerator.generateNumber(CVV_LENGTH);
        }
        while (!isCvvUnique(cvv));

        return cvv;
    }

    private boolean isCvvUnique(String cvv) {
        return !cardRepository.existsByCvv(cvv);
    }
}
