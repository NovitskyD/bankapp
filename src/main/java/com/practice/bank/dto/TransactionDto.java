package com.practice.bank.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDto {
    private String id;
    private AccountDto senderAccount;
    private AccountDto recipientAccount;
    @NonNull private String type;
    @NonNull private BigDecimal amount;
    @NonNull private String currency;
    private String description;
    @NonNull private String timestamp;
    @NonNull private String status;
}
