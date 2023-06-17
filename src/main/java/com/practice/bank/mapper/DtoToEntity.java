package com.practice.bank.mapper;

import com.practice.bank.dto.*;
import com.practice.bank.entity.*;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DtoToEntity {
    public static ClientEntity clientsDtoToEntity(ClientDto clientDto){
        return ClientEntity.builder()
                .firstName(clientDto.getFirstName())
                .lastName(clientDto.getLastName())
                .birthDate(clientDto.getBirthDate().atStartOfDay(ZoneId.systemDefault()).toInstant())
                .address(clientDto.getAddress())
                .phone(clientDto.getPhone())
                .email(clientDto.getEmail())
                .password(clientDto.getPassword())
                .accounts(accountsDtoToEntityList(clientDto.getAccounts()))
                .build();
    }

    public static AccountEntity accountsDtoToEntityForClients(AccountDto accountDto){
        return AccountEntity.builder()
//                .client(clientsDtoToEntity(accountDto.getClient()))
                .accountNumber(accountDto.getAccountNumber())
                .accountType(accountDto.getAccountType())
                .balance(accountDto.getBalance())
                .cards(cardsDtoToEntityList(accountDto.getCards()))
                .loans(loansDtoToEntityList(accountDto.getLoans()))
                .sentTransactions(transactionsDtoToEntityList(accountDto.getSentTransactions()))
                .receivedTransactions(transactionsDtoToEntityList(accountDto.getReceivedTransactions()))
                .build();
    }

    public static AccountEntity accountsDtoToEntity(AccountDto accountDto){
        return AccountEntity.builder()
                .client(clientsDtoToEntity(accountDto.getClient()))
                .accountNumber(accountDto.getAccountNumber())
                .accountType(accountDto.getAccountType())
                .balance(accountDto.getBalance())
                .cards(cardsDtoToEntityList(accountDto.getCards()))
                .loans(loansDtoToEntityList(accountDto.getLoans()))
                .sentTransactions(transactionsDtoToEntityList(accountDto.getSentTransactions()))
                .receivedTransactions(transactionsDtoToEntityList(accountDto.getReceivedTransactions()))
                .build();
    }

    public static CardEntity cardsDtoToEntity(CardDto cardDto){
        return CardEntity.builder()
                .account(accountsDtoToEntityForClients(cardDto.getAccount()))
                .cardNumber(cardDto.getCardNumber())
                .expirationDate(cardDto.getExpirationDate())
                .holderName(cardDto.getHolderName())
                .cvv(cardDto.getCvv())
                .status(cardDto.getStatus())
                .build();
    }

    public static LoanEntity loansDtoToEntity(LoanDto loanDto){
        return LoanEntity.builder()
                .account(accountsDtoToEntityForClients(loanDto.getAccount()))
                .loanAmount(loanDto.getLoanAmount())
                .interestRate(loanDto.getInterestRate())
                .termMonths(loanDto.getTermMonths())
                .startDate(loanDto.getStartDate())
                .endDate(loanDto.getEndDate())
                .status(loanDto.getStatus())
                .build();
    }

    public static TransactionEntity transactionsDtoToEntity(TransactionDto transactionDto){
        return TransactionEntity.builder()
                .senderAccount(accountsDtoToEntityForClients(transactionDto.getSenderAccount()))
                .recipientAccount(accountsDtoToEntityForClients(transactionDto.getRecipientAccount()))
                .type(transactionDto.getType())
                .amount(transactionDto.getAmount())
                .currency(transactionDto.getCurrency())
                .description(transactionDto.getDescription())
                .timestamp(transactionDto.getTimestamp())
                .status(transactionDto.getStatus())
                .build();
    }

    public static ReportEntity reportsDtoToEntity(ReportDto reportDto){
        return ReportEntity.builder()
                .account(accountsDtoToEntityForClients(reportDto.getAccount()))
                .title(reportDto.getTitle())
                .description(reportDto.getDescription())
                .createdAt(reportDto.getCreatedAt())
                .updatedAt(reportDto.getUpdatedAt())
                .build();
    }

    public static List<ClientEntity> clientsDtoToEntityList(List<ClientDto> clientDtos){
        if(clientDtos == null){
            return new ArrayList<>();
        }
        return clientDtos.stream()
                .map(DtoToEntity::clientsDtoToEntity)
                .collect(Collectors.toList());
    }

    public static List<AccountEntity> accountsDtoToEntityList(List<AccountDto> accountDtos){
        if(accountDtos == null){
            return new ArrayList<>();
        }
        return accountDtos.stream()
                .map(DtoToEntity::accountsDtoToEntityForClients)
                .collect(Collectors.toList());
    }

    public static List<CardEntity> cardsDtoToEntityList(List<CardDto> cardDtos){
        if(cardDtos == null){
            return new ArrayList<>();
        }
        return cardDtos.stream()
                .map(DtoToEntity::cardsDtoToEntity)
                .collect(Collectors.toList());
    }

    public static List<LoanEntity> loansDtoToEntityList(List<LoanDto> loanDtos){
        if(loanDtos == null){
            return new ArrayList<>();
        }
        return loanDtos.stream()
                .map(DtoToEntity::loansDtoToEntity)
                .collect(Collectors.toList());
    }

    public static List<TransactionEntity> transactionsDtoToEntityList(List<TransactionDto> transactionDtos){
        if(transactionDtos == null){
            return new ArrayList<>();
        }
        return transactionDtos.stream()
                .map(DtoToEntity::transactionsDtoToEntity)
                .collect(Collectors.toList());
    }
}
