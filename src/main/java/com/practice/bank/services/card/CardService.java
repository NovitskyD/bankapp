package com.practice.bank.services.card;

import com.practice.bank.dto.CardDto;
import com.practice.bank.dto.TopUpDto;
import com.practice.bank.services.BankService;

import java.time.LocalDate;
import java.util.List;

public interface CardService extends BankService<CardDto> {
    List<CardDto> getCardsByAccountId(String accountId);
    CardDto createCardDtoForForm(String accountId);
    void topUpBalance(TopUpDto topUpDto);
    List<CardDto> getAllCardsForClient(String clientId);
    CardDto getCardByCardNumberAndCvvAndExpirationDate(String cardNumber, String cvv, LocalDate expirationDate);
    CardDto getCardByCardNumber(String cardNumber);
}
