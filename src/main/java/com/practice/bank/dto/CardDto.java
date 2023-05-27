package com.practice.bank.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardDto {
    private String id;
    private AccountDto account;
    @NonNull private String cardNumber;
    private LocalDate expirationDate;
    private String holderName;
    private String cvv;
    private String status;
}
