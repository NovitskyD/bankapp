package com.practice.bank.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto {
    private String id;
    private ClientDto client;
    @NonNull private String accountNumber;
    @NonNull private String accountType;
    @NonNull private BigDecimal balance;
    private List<CardDto> cards;
    private List<LoanDto> loans;
    private List<TransactionDto> sentTransactions;
    private List<TransactionDto> receivedTransactions;
}
