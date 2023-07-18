package com.practice.bank.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TopUpDto {
    @NotEmpty(message = "Card number is required")
    private String cardNumber;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "5", message = "Amount must be greater than or equal to 5")
    @Digits(integer = 12, fraction = 2, message = "Amount must have 2 decimal places")
    private BigDecimal amount;

    @NotNull
    private CurrencyDto currency;
}
