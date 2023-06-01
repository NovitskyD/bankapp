package com.practice.bank.mapper;

import com.practice.bank.dto.*;
import com.practice.bank.entity.*;

import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

public class EntityToDto {
    public static ClientDto clientsEntityToDto(ClientEntity clientEntity){
        return ClientDto.builder()
                .id(clientEntity.getId().toString())
                .firstName(clientEntity.getFirstName())
                .lastName(clientEntity.getLastName())
                .birthDate(clientEntity.getBirthDate().atZone(ZoneId.systemDefault()).toLocalDate())
                .address(clientEntity.getAddress())
                .phone(clientEntity.getPhone())
                .email(clientEntity.getEmail())
                .password(clientEntity.getPassword())
                .accounts(accountsEntitiesToDtoList(clientEntity.getAccounts()))
                .build();
    }

    public static AccountDto accountsEntityToDto(AccountEntity accountEntity){
        return AccountDto.builder()
                .id(accountEntity.getId().toString())
                .client(clientsEntityToDto(accountEntity.getClient()))
                .accountNumber(accountEntity.getAccountNumber())
                .accountType(accountEntity.getAccountType())
                .balance(accountEntity.getBalance())
                .cards(cardsEntitiesToDtoList(accountEntity.getCards()))
                .loans(loansEntityToDtoList(accountEntity.getLoans()))
                .sentTransactions(transactionsEntityToDtoList(accountEntity.getSentTransactions()))
                .receivedTransactions(transactionsEntityToDtoList(accountEntity.getReceivedTransactions()))
                .build();
    }

    public static CardDto cardsEntityToDto(CardEntity cardEntity){
        return CardDto.builder()
                .id(cardEntity.getId().toString())
                .account(accountsEntityToDto(cardEntity.getAccount()))
                .cardNumber(cardEntity.getCardNumber())
                .expirationDate(cardEntity.getExpirationDate())
                .holderName(cardEntity.getHolderName())
                .cvv(cardEntity.getCvv())
                .status(cardEntity.getStatus())
                .build();
    }

    public static LoanDto loansEntityToDto(LoanEntity loanEntity){
        return LoanDto.builder()
                .id(loanEntity.getId().toString())
                .account(accountsEntityToDto(loanEntity.getAccount()))
                .loanAmount(loanEntity.getLoanAmount())
                .interestRate(loanEntity.getInterestRate())
                .termMonths(loanEntity.getTermMonths())
                .startDate(loanEntity.getStartDate())
                .endDate(loanEntity.getEndDate())
                .status(loanEntity.getStatus())
                .build();
    }

    public static TransactionDto transactionsEntityToDto(TransactionEntity transactionEntity){
        return TransactionDto.builder()
                .id(transactionEntity.getId().toString())
                .senderAccount(accountsEntityToDto(transactionEntity.getSenderAccount()))
                .recipientAccount(accountsEntityToDto(transactionEntity.getRecipientAccount()))
                .type(transactionEntity.getType())
                .amount(transactionEntity.getAmount())
                .currency(transactionEntity.getCurrency())
                .description(transactionEntity.getDescription())
                .timestamp(transactionEntity.getTimestamp())
                .status(transactionEntity.getStatus())
                .build();
    }

    public static ReportDto reportsEntityToDto(ReportEntity reportEntity){
        return ReportDto.builder()
                .id(reportEntity.getId().toString())
                .account(accountsEntityToDto(reportEntity.getAccount()))
                .title(reportEntity.getTitle())
                .description(reportEntity.getDescription())
                .createdAt(reportEntity.getCreatedAt())
                .updatedAt(reportEntity.getUpdatedAt())
                .build();
    }

    public static List<ClientDto> clientsEntitiesToDtoList(List<ClientEntity> clientEntities){
        return clientEntities.stream()
                .map(EntityToDto::clientsEntityToDto)
                .collect(Collectors.toList());
    }

    public static List<AccountDto> accountsEntitiesToDtoList(List<AccountEntity> accountEntities){
        return accountEntities.stream()
                .map(EntityToDto::accountsEntityToDto)
                .collect(Collectors.toList());
    }

    public static List<CardDto> cardsEntitiesToDtoList(List<CardEntity> cardEntities){
        return cardEntities.stream()
                .map(EntityToDto::cardsEntityToDto)
                .collect(Collectors.toList());
    }

    public static List<LoanDto> loansEntityToDtoList(List<LoanEntity> loanEntities){
        return loanEntities.stream()
                .map(EntityToDto::loansEntityToDto)
                .collect(Collectors.toList());
    }

    public static List<TransactionDto> transactionsEntityToDtoList(List<TransactionEntity> transactionEntities){
        return transactionEntities.stream()
                .map(EntityToDto::transactionsEntityToDto)
                .collect(Collectors.toList());
    }
}
