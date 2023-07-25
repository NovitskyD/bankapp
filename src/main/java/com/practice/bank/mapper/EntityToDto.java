package com.practice.bank.mapper;

import com.practice.bank.dto.*;
import com.practice.bank.entity.*;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EntityToDto {
    public static ClientDto clientsEntityToDto(ClientEntity clientEntity){
        if(clientEntity == null){
            return null;
        }

        return ClientDto.builder()
                .id(clientEntity.getId().toString())
                .firstName(clientEntity.getFirstName())
                .lastName(clientEntity.getLastName())
                .birthDate(clientEntity.getBirthDate().atZone(ZoneId.systemDefault()).toLocalDate())
                .address(clientEntity.getAddress())
                .phone(clientEntity.getPhone())
                .email(clientEntity.getEmail())
                .password(clientEntity.getPassword())
                .role(clientEntity.getRole())
                .status(clientEntity.getStatus())
                .accounts(accountsEntitiesToDtoList(clientEntity.getAccounts()))
                .build();
    }

    public static ClientDto clientsEntityToDtoForAccounts(ClientEntity clientEntity){
        if(clientEntity == null){
            return null;
        }

        return ClientDto.builder()
                .id(clientEntity.getId().toString())
                .firstName(clientEntity.getFirstName())
                .lastName(clientEntity.getLastName())
                .birthDate(clientEntity.getBirthDate().atZone(ZoneId.systemDefault()).toLocalDate())
                .address(clientEntity.getAddress())
                .phone(clientEntity.getPhone())
                .email(clientEntity.getEmail())
                .password(clientEntity.getPassword())
                .role(clientEntity.getRole())
                .status(clientEntity.getStatus())
                .build();
    }

    public static AccountDto accountsEntityToDtoForClients(AccountEntity accountEntity){
        if(accountEntity == null){
            return null;
        }
        return AccountDto.builder()
                .id(accountEntity.getId().toString())
                .client(clientsEntityToDtoForAccounts(accountEntity.getClient()))
                .accountNumber(accountEntity.getAccountNumber())
                .accountType(accountEntity.getAccountType())
                .cards(cardsEntitiesToDtoList(accountEntity.getCards()))
                .build();
    }

    public static AccountDto accountsEntityToDto(AccountEntity accountEntity){
        if(accountEntity == null){
            return null;
        }
        return AccountDto.builder()
                .id(accountEntity.getId().toString())
                .client(clientsEntityToDto(accountEntity.getClient()))
                .accountNumber(accountEntity.getAccountNumber())
                .accountType(accountEntity.getAccountType())
                .cards(cardsEntitiesToDtoList(accountEntity.getCards()))
                .build();
    }

    public static AccountDto accountsEntityToDtoForCards(AccountEntity accountEntity){
        if(accountEntity == null){
            return null;
        }
        return AccountDto.builder()
                .id(accountEntity.getId().toString())
                .client(clientsEntityToDtoForAccounts(accountEntity.getClient()))
                .accountNumber(accountEntity.getAccountNumber())
                .accountType(accountEntity.getAccountType())
//                .cards(cardsEntitiesToDtoList(accountEntity.getCards()))
                .build();
    }

    public static CardDto cardsEntityToDto(CardEntity cardEntity){
        return CardDto.builder()
                .id(cardEntity.getId().toString())
                .account(accountsEntityToDtoForCards(cardEntity.getAccount()))
                .loan(loansEntityToDto(cardEntity.getLoan()))
                .cardNumber(cardEntity.getCardNumber())
                .expirationDate(cardEntity.getExpirationDate())
                .holderName(cardEntity.getHolderName())
                .cvv(cardEntity.getCvv())
                .balance(cardEntity.getBalance())
                .currency(currencyEntityToDto(cardEntity.getCurrency()))
                .status(cardEntity.getStatus())
                .limit(limitEntityToDto(cardEntity.getLimit()))
                .paymentSystem(paymentSystemEntityToDto(cardEntity.getPaymentSystem()))
                .build();
    }

    public static CardDto cardsEntityToDtoForLoan(CardEntity cardEntity){
        if(cardEntity == null){
            return null;
        }
        return CardDto.builder()
                .id(cardEntity.getId().toString())
                .account(accountsEntityToDtoForCards(cardEntity.getAccount()))
//                .loan(loansEntityToDto(cardEntity.getLoan()))
                .cardNumber(cardEntity.getCardNumber())
                .expirationDate(cardEntity.getExpirationDate())
                .holderName(cardEntity.getHolderName())
                .cvv(cardEntity.getCvv())
                .balance(cardEntity.getBalance())
                .currency(currencyEntityToDto(cardEntity.getCurrency()))
                .status(cardEntity.getStatus())
                .limit(limitEntityToDto(cardEntity.getLimit()))
                .paymentSystem(paymentSystemEntityToDto(cardEntity.getPaymentSystem()))
                .build();
    }

    public static LoanDto loansEntityToDto(LoanEntity loanEntity){
        if(loanEntity == null){
            return null;
        }
        return LoanDto.builder()
                .id(loanEntity.getId().toString())
                .card(cardsEntityToDtoForLoan(loanEntity.getCard()))
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
                .senderCard(cardsEntityToDto(transactionEntity.getSenderCard()))
                .recipientCard(cardsEntityToDto(transactionEntity.getRecipientCard()))
                .type(transactionEntity.getType())
                .amount(transactionEntity.getAmount())
                .currency(currencyEntityToDto(transactionEntity.getCurrency()))
                .description(transactionEntity.getDescription())
                .timestamp(transactionEntity.getTimestamp())
                .status(transactionEntity.getStatus())
                .build();
    }

    public static ReportDto reportsEntityToDto(ReportEntity reportEntity){
        return ReportDto.builder()
                .id(reportEntity.getId().toString())
                .account(accountsEntityToDtoForClients(reportEntity.getAccount()))
                .title(reportEntity.getTitle())
                .description(reportEntity.getDescription())
                .createdAt(reportEntity.getCreatedAt())
                .updatedAt(reportEntity.getUpdatedAt())
                .build();
    }

    public static List<ClientDto> clientsEntitiesToDtoList(List<ClientEntity> clientEntities){
        if(clientEntities == null){
            return new ArrayList<>();
        }
        return clientEntities.stream()
                .map(EntityToDto::clientsEntityToDto)
                .collect(Collectors.toList());
    }

    public static List<AccountDto> accountsEntitiesToDtoList(List<AccountEntity> accountEntities){
        if(accountEntities == null){
            return new ArrayList<>();
        }
        return accountEntities.stream()
                .map(EntityToDto::accountsEntityToDtoForClients)
                .collect(Collectors.toList());
    }

    public static List<CardDto> cardsEntitiesToDtoList(List<CardEntity> cardEntities){
        if(cardEntities == null){
            return new ArrayList<>();
        }
        return cardEntities.stream()
                .map(EntityToDto::cardsEntityToDto)
                .collect(Collectors.toList());
    }

    public static List<LoanDto> loansEntityToDtoList(List<LoanEntity> loanEntities){
        if(loanEntities == null){
            return new ArrayList<>();
        }
        return loanEntities.stream()
                .map(EntityToDto::loansEntityToDto)
                .collect(Collectors.toList());
    }

    public static List<TransactionDto> transactionsEntityToDtoList(List<TransactionEntity> transactionEntities){
        if(transactionEntities == null){
            return new ArrayList<>();
        }
        return transactionEntities.stream()
                .map(EntityToDto::transactionsEntityToDto)
                .collect(Collectors.toList());
    }

    public static CurrencyDto currencyEntityToDto(CurrencyEntity currencyEntity){
        if(currencyEntity == null){
            return null;
        }
        return CurrencyDto.builder()
                .id(currencyEntity.getId().toString())
                .code(currencyEntity.getCode())
                .name(currencyEntity.getName())
                .build();
    }

    public static List<CurrencyDto> currencyEntityToDtoList(List<CurrencyEntity> currencyEntities){
        if(currencyEntities == null){
            return new ArrayList<>();
        }
        return currencyEntities.stream()
                .map(EntityToDto::currencyEntityToDto)
                .collect(Collectors.toList());
    }

    public static CurrencyRateDto currencyRateDtoToEntity(CurrencyRateEntity currencyRateEntity){
        if(currencyRateEntity == null){
            return null;
        }
        return CurrencyRateDto.builder()
                .id(currencyRateEntity.getId().toString())
                .baseCurrency(currencyEntityToDto(currencyRateEntity.getBaseCurrency()))
                .targetCurrency(currencyEntityToDto(currencyRateEntity.getTargetCurrency()))
                .rate(currencyRateEntity.getRate())
                .date(currencyRateEntity.getDate())
                .build();
    }

    public static List<CurrencyRateDto> currencyRateDtoToEntityList(List<CurrencyRateEntity> currencyRateEntities){
        if(currencyRateEntities == null){
            return new ArrayList<>();
        }
        return currencyRateEntities.stream()
                .map(EntityToDto::currencyRateDtoToEntity)
                .collect(Collectors.toList());
    }

    public static CountryDto countryEntityToDto(CountryEntity countryEntity){
        if (countryEntity == null){
            return null;
        }
        return CountryDto.builder()
                .id(countryEntity.getId().toString())
                .name(countryEntity.getName())
                .build();
    }

    public static PaymentSystemDto paymentSystemEntityToDto(PaymentSystemEntity entity){
        if(entity == null){
            return null;
        }
        List<PaymentSystemCountryDto> countryDtos = new ArrayList<>();
        if(entity.getCountries() != null) {
            countryDtos = entity.getCountries().stream()
                    .map(EntityToDto::paymentSystemCountryEntityToDto)
                    .collect(Collectors.toList());
        }

             PaymentSystemDto dto = PaymentSystemDto.builder()
                .id(entity.getId().toString())
                .name(entity.getName())
                .description(entity.getDescription())
                .logoUrl(entity.getLogoUrl())
                .transactionFeePercentage(entity.getTransactionFeePercentage())
                .countries(countryDtos)
                .build();

        for (PaymentSystemCountryDto countryDto : countryDtos){
            countryDto.setPaymentSystem(dto);
        }
        return dto;
    }

    public static PaymentSystemCountryDto paymentSystemCountryEntityToDto(PaymentSystemCountryEntity entity){
        if(entity == null){
            return null;
        }

        return PaymentSystemCountryDto.builder()
                .id(paymentSystemCountryIdEntityToDto(entity.getId()))
                .country(countryEntityToDto(entity.getCountry()))
                .build();
    }

    public static PaymentSystemCountryIdDto paymentSystemCountryIdEntityToDto(PaymentSystemCountryId entity){
        if(entity == null){
            return null;
        }
        return new PaymentSystemCountryIdDto(entity.getPaymentSystemId().toString(), entity.getCountryId().toString());
    }

    public static CardDto cardsEntityToDtoForLimit(CardEntity cardEntity){
        return CardDto.builder()
                .id(cardEntity.getId().toString())
//                .account(accountsEntityToDtoForClients(cardEntity.getAccount()))
                .cardNumber(cardEntity.getCardNumber())
                .expirationDate(cardEntity.getExpirationDate())
                .holderName(cardEntity.getHolderName())
                .cvv(cardEntity.getCvv())
                .balance(cardEntity.getBalance())
                .status(cardEntity.getStatus())
//                .limit(limitEntityToDto(cardEntity.getLimit()))
                .paymentSystem(paymentSystemEntityToDto(cardEntity.getPaymentSystem()))
                .build();
    }

    public static List<CardDto> cardsEntitiesToDtoListForLimit(List<CardEntity> cardEntities){
        if(cardEntities == null){
            return new ArrayList<>();
        }
        return cardEntities.stream()
                .map(EntityToDto::cardsEntityToDtoForLimit)
                .collect(Collectors.toList());
    }

    public static LimitDto limitEntityToDto(LimitEntity limitEntity){
        if(limitEntity == null){
            return null;
        }
        return LimitDto.builder()
                .id(limitEntity.getId().toString())
                .dailyLimit(limitEntity.getDailyLimit())
                .monthlyLimit(limitEntity.getMonthlyLimit())
                .transactionLimit(limitEntity.getTransactionLimit())
                .card(cardsEntitiesToDtoListForLimit(limitEntity.getCards()))
                .build();
    }

    public static List<PaymentSystemDto> paymentSystemEntityToDtoList(List<PaymentSystemEntity> paymentSystemEntities){
        if(paymentSystemEntities == null){
            return new ArrayList<>();
        }
        return paymentSystemEntities.stream()
                .map(EntityToDto::paymentSystemEntityToDto)
                .collect(Collectors.toList());
    }

    public static List<LimitDto> limitToDtoList(List<LimitEntity> limitEntities){
        if(limitEntities == null){
            return new ArrayList<>();
        }
        return limitEntities.stream()
                .map(EntityToDto::limitEntityToDto)
                .collect(Collectors.toList());
    }
}

