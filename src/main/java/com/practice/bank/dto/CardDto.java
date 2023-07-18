package com.practice.bank.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardDto {
    private String id;
    private AccountDto account;
    private LoanDto loan;
    private String cardNumber;
    private LocalDate expirationDate;
    private String holderName;
    private String cvv;
    private BigDecimal balance;
    private CurrencyDto currency;
    private String status;
    private LimitDto limit;
    private PaymentSystemDto paymentSystem;
}
