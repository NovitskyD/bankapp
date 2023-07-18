package com.practice.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto {
    private String id;
    private ClientDto client;
    private String accountNumber;
    private String accountType;
    private List<CardDto> cards;
}
