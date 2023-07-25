package com.practice.bank.services.payment;

import com.practice.bank.dto.PaymentDto;
import com.practice.bank.entity.CardEntity;
import com.practice.bank.repository.CardRepository;
import com.practice.bank.services.payment.PaymentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final CardRepository cardRepository;
    @Override
    public void processPayment(PaymentDto paymentDto) {
        CardEntity card = cardRepository.findById(UUID.fromString(paymentDto.getCardId()))
                .orElseThrow(() -> new EntityNotFoundException("Card was not found"));

        BigDecimal paymentAmount = paymentDto.getAmount();
        BigDecimal currentBalance = card.getBalance();
        BigDecimal newBalance = currentBalance.subtract(paymentAmount);
        card.setBalance(newBalance);

        cardRepository.save(card);
    }
}
