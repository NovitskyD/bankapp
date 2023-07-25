package com.practice.bank.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDto {
    private String id;
    private CardDto senderCard;
    private CardDto recipientCard;
    @NonNull private String type;
    @NonNull private BigDecimal amount;
    @NonNull private CurrencyDto currency;
    private String description;
    @NonNull private String timestamp;
    @NonNull private String status;
}
