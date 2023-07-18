package com.practice.bank.services;

import com.practice.bank.dto.PaymentDto;
import com.practice.bank.entity.CardEntity;
import com.practice.bank.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService{
    private final CardRepository cardRepository;
    private final LoanService loanService;
    private final PaymentService paymentService;

    @Override
    public void processCreditPayments() {
        List<CardEntity> creditCards = cardRepository.findCreditCardsWithOutstandingLoan();

        for (CardEntity card : creditCards){
            BigDecimal balance = card.getBalance();
            BigDecimal loanAmount = card.getLoan().getLoanAmount();

            if(balance.compareTo(loanAmount) < 0){
                BigDecimal outstandingAmount = loanAmount.subtract(balance);
                BigDecimal paymentAmount = calculateInterest(outstandingAmount, card.getLoan().getInterestRate());

                PaymentDto paymentDto = PaymentDto.builder()
                        .cardId(card.getId().toString())
                        .amount(paymentAmount)
                        .build();

                paymentService.processPayment(paymentDto);
            }
        }
    }

    private BigDecimal calculateInterest(BigDecimal amount, BigDecimal interestRate) {
        BigDecimal interest = amount.multiply(interestRate.divide(BigDecimal.valueOf(100)));

        interest = interest.setScale(2, RoundingMode.HALF_UP);

        return interest;
    }
}
