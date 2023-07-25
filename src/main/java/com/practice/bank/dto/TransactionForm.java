package com.practice.bank.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransactionForm {
    @NotEmpty(message = "Sender card number is required")
    private String senderCardNumber;
    @NotEmpty(message = "Sender CVV is required")
    private String senderCvv;
    @Future(message = "Sender expiration date must be in the future")
    private LocalDate senderExpirationDate;
    @NotEmpty(message = "Recipient card number is required")
    private String recipientCardNumber;
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "5", inclusive = true, message = "Amount must be greater than or equal to 5")
    @Digits(integer = 9, fraction = 2, message = "Amount must have up to 9 integer digits and 2 decimal digits")
    private BigDecimal amount;
}
