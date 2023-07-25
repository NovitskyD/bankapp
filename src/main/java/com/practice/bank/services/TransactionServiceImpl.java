package com.practice.bank.services;

import com.practice.bank.dto.CardDto;
import com.practice.bank.dto.TransactionDto;
import com.practice.bank.dto.TransactionForm;
import com.practice.bank.mapper.DtoToEntity;
import com.practice.bank.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{
    private final CardService cardService;
    private final CurrencyService currencyService;
    private final TransactionRepository transactionRepository;

    @Override
    public TransactionDto getDataById(String id) {
        return null;
    }

    @Override
    public void insertData(TransactionDto element) {
        transactionRepository.save(DtoToEntity.transactionsDtoToEntity(element));
    }

    @Override
    public void updateData(TransactionDto element) {

    }

    @Override
    @Transactional
    public TransactionDto processTransaction(TransactionForm transactionForm) {
        CardDto senderCard = cardService.getCardByCardNumberAndCvvAndExpirationDate(
                transactionForm.getSenderCardNumber(),
                transactionForm.getSenderCvv(),
                transactionForm.getSenderExpirationDate()
        );
        CardDto recipientCard = cardService.getCardByCardNumber(transactionForm.getRecipientCardNumber());

        BigDecimal recipientBalance = recipientCard.getBalance();
        BigDecimal senderBalance;
        BigDecimal newRecipientBalance;
        String senderCurrency = senderCard.getCurrency().getCode();
        String recipientCurrency = recipientCard.getCurrency().getCode();
        BigDecimal transactionFeePercentage = senderCard.getPaymentSystem().getTransactionFeePercentage();

        BigDecimal amount = transactionForm.getAmount();
        BigDecimal baseAmount;

        if (!senderCurrency.equals(recipientCurrency)) {
            BigDecimal exchangeRate = currencyService.getExchangeRate(senderCurrency, recipientCurrency);
            baseAmount = amount.multiply(exchangeRate);
        } else {
            baseAmount = amount;
        }

        BigDecimal transactionFee = baseAmount.multiply(transactionFeePercentage);
        senderBalance = senderCard.getBalance().subtract(transactionFee);
        newRecipientBalance = recipientBalance.add(baseAmount);

        senderCard.setBalance(senderBalance);
        recipientCard.setBalance(newRecipientBalance);

        cardService.updateData(senderCard);
        cardService.updateData(recipientCard);

        return TransactionDto.builder()
                .senderCard(senderCard)
                .recipientCard(recipientCard)
                .currency(senderCard.getCurrency())
                .amount(amount)
                .type("Transfer")
                .timestamp(Instant.now().toString())
                .status("Done")
                .build();
    }

}
