package com.practice.bank.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto {
    private String id;
    private ClientDto client;
    private String accountNumber = UUID.randomUUID().toString();
    private String accountType;
    private BigDecimal balance = BigDecimal.ZERO;
    private List<CardDto> cards;
    private List<LoanDto> loans;
    private List<TransactionDto> sentTransactions;
    private List<TransactionDto> receivedTransactions;
}
