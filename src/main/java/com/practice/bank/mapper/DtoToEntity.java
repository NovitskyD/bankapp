package com.practice.bank.mapper;

import com.practice.bank.dto.*;
import com.practice.bank.entity.*;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DtoToEntity {
    public static ClientEntity clientsDtoToEntity(ClientDto clientDto){
        if(clientDto == null){
            return null;
        }
        return ClientEntity.builder()
                .firstName(clientDto.getFirstName())
                .lastName(clientDto.getLastName())
                .birthDate(clientDto.getBirthDate().atStartOfDay(ZoneId.systemDefault()).toInstant())
                .address(clientDto.getAddress())
                .phone(clientDto.getPhone())
                .email(clientDto.getEmail())
                .password(clientDto.getPassword())
                .role(clientDto.getRole())
                .status(clientDto.getStatus())
                .accounts(accountsDtoToEntityList(clientDto.getAccounts()))
                .build();
    }

    public static ClientEntity clientsDtoToEntityForAccounts(ClientDto clientDto){
        if(clientDto == null){
            return null;
        }
        return ClientEntity.builder()
                .firstName(clientDto.getFirstName())
                .lastName(clientDto.getLastName())
                .birthDate(clientDto.getBirthDate().atStartOfDay(ZoneId.systemDefault()).toInstant())
                .address(clientDto.getAddress())
                .phone(clientDto.getPhone())
                .email(clientDto.getEmail())
                .password(clientDto.getPassword())
                .role(clientDto.getRole())
                .status(clientDto.getStatus())
                .build();
    }

    public static AccountEntity accountsDtoToEntityForClients(AccountDto accountDto){
        return AccountEntity.builder()
                .client(clientsDtoToEntityForAccounts(accountDto.getClient()))
                .accountNumber(accountDto.getAccountNumber())
                .accountType(accountDto.getAccountType())
                .cards(cardsDtoToEntityList(accountDto.getCards()))
                .build();
    }

    public static AccountEntity accountsDtoToEntity(AccountDto accountDto){
        if(accountDto == null){
            return null;
        }
        return AccountEntity.builder()
                .client(clientsDtoToEntity(accountDto.getClient()))
                .accountNumber(accountDto.getAccountNumber())
                .accountType(accountDto.getAccountType())
                .cards(cardsDtoToEntityList(accountDto.getCards()))
                .build();
    }

    public static CardEntity cardsDtoToEntity(CardDto cardDto){
        if(cardDto == null){
            return null;
        }
        return CardEntity.builder()
                .account(accountsDtoToEntity(cardDto.getAccount()))
                .loan(loansDtoToEntity(cardDto.getLoan()))
                .cardNumber(cardDto.getCardNumber())
                .expirationDate(cardDto.getExpirationDate())
                .holderName(cardDto.getHolderName())
                .cvv(cardDto.getCvv())
                .balance(cardDto.getBalance())
                .currency(currencyDtoToEntity(cardDto.getCurrency()))
                .status(cardDto.getStatus())
                .limit(limitDtoToEntity(cardDto.getLimit()))
                .paymentSystem(paymentSystemDtoToEntity(cardDto.getPaymentSystem()))
                .build();
    }

    public static LoanEntity loansDtoToEntity(LoanDto loanDto){
        if(loanDto == null){
            return null;
        }
        return LoanEntity.builder()
                .card(cardsDtoToEntityForLoan(loanDto.getCard()))
                .loanAmount(loanDto.getLoanAmount())
                .interestRate(loanDto.getInterestRate())
                .termMonths(loanDto.getTermMonths())
                .startDate(loanDto.getStartDate())
                .endDate(loanDto.getEndDate())
                .status(loanDto.getStatus())
                .build();
    }

    public static CardEntity cardsDtoToEntityForLoan(CardDto cardDto){
        if(cardDto == null){
            return null;
        }
        return CardEntity.builder()
                .account(accountsDtoToEntity(cardDto.getAccount()))
//                .loan(loansDtoToEntityForCards(cardDto.getLoan()))
                .cardNumber(cardDto.getCardNumber())
                .expirationDate(cardDto.getExpirationDate())
                .holderName(cardDto.getHolderName())
                .cvv(cardDto.getCvv())
                .balance(cardDto.getBalance())
                .currency(currencyDtoToEntity(cardDto.getCurrency()))
                .status(cardDto.getStatus())
                .limit(limitDtoToEntity(cardDto.getLimit()))
                .paymentSystem(paymentSystemDtoToEntity(cardDto.getPaymentSystem()))
                .build();
    }



    public static TransactionEntity transactionsDtoToEntity(TransactionDto transactionDto){
        return TransactionEntity.builder()
                .senderCard(cardsDtoToEntity(transactionDto.getSenderCard()))
                .recipientCard(cardsDtoToEntity(transactionDto.getRecipientCard()))
                .type(transactionDto.getType())
                .amount(transactionDto.getAmount())
                .currency(currencyDtoToEntity(transactionDto.getCurrency()))
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

    public static CurrencyEntity currencyDtoToEntity(CurrencyDto currencyDto){
        if(currencyDto == null){
            return null;
        }
        return CurrencyEntity.builder()
                .code(currencyDto.getCode())
                .name(currencyDto.getName())
                .build();
    }

    public static List<CurrencyEntity> currencyDtoToEntityList(List<CurrencyDto> currencyDtos){
        if(currencyDtos == null){
            return new ArrayList<>();
        }
        return currencyDtos.stream()
                .map(DtoToEntity::currencyDtoToEntity)
                .collect(Collectors.toList());
    }

    public static CurrencyRateEntity currencyRateDtoToEntity(CurrencyRateDto currencyRateDto){
        if(currencyRateDto == null){
            return null;
        }
        return CurrencyRateEntity.builder()
                .baseCurrency(currencyDtoToEntity(currencyRateDto.getBaseCurrency()))
                .targetCurrency(currencyDtoToEntity(currencyRateDto.getTargetCurrency()))
                .rate(currencyRateDto.getRate())
                .date(currencyRateDto.getDate())
                .build();
    }

    public static List<CurrencyRateEntity> currencyRateDtoToEntityList(List<CurrencyRateDto> currencyRateDtos){
        if(currencyRateDtos == null){
            return new ArrayList<>();
        }
        return currencyRateDtos.stream()
                .map(DtoToEntity::currencyRateDtoToEntity)
                .collect(Collectors.toList());
    }

    public static CountryEntity countryDtoToEntity(CountryDto countryDto){
        if(countryDto == null){
            return null;
        }
        return CountryEntity.builder()
                .name(countryDto.getName())
                .build();
    }

    public static PaymentSystemEntity paymentSystemDtoToEntity(PaymentSystemDto paymentSystemDto){
        if(paymentSystemDto == null){
            return null;
        }
        List<PaymentSystemCountryEntity> countryEntities = new ArrayList<>();
        if (paymentSystemDto.getCountries() != null) {
            countryEntities = paymentSystemDto.getCountries().stream()
                    .map(DtoToEntity::paymentSystemCountryDtoToEntity)
                    .collect(Collectors.toList());
        }

        PaymentSystemEntity entity = PaymentSystemEntity.builder()
                .name(paymentSystemDto.getName())
                .description(paymentSystemDto.getDescription())
                .logoUrl(paymentSystemDto.getLogoUrl())
                .transactionFeePercentage(paymentSystemDto.getTransactionFeePercentage())
                .countries(countryEntities)
                .build();

        for (PaymentSystemCountryEntity countryEntity : countryEntities) {
            countryEntity.setPaymentSystem(entity);
        }

        return entity;
    }

    public static PaymentSystemCountryEntity paymentSystemCountryDtoToEntity(PaymentSystemCountryDto dto){
        if (dto == null){
            return null;
        }
        return PaymentSystemCountryEntity.builder()
                .id(paymentSystemCountryIdDtoToEntity(dto.getId()))
                .country(countryDtoToEntity(dto.getCountry()))
                .build();
    }

    public static PaymentSystemCountryId paymentSystemCountryIdDtoToEntity(PaymentSystemCountryIdDto dto){
        if (dto == null){
            return null;
        }
        return new PaymentSystemCountryId(Long.valueOf(dto.getPaymentSystemId()), Long.valueOf(dto.getCountryId()));
    }

    public static LimitEntity limitDtoToEntity(LimitDto limitDto){
        if (limitDto == null){
            return null;
        }
        return LimitEntity.builder()
                .dailyLimit(limitDto.getDailyLimit())
                .monthlyLimit(limitDto.getMonthlyLimit())
                .transactionLimit(limitDto.getTransactionLimit())
                .cards(cardsDtoToEntityList(limitDto.getCard()))
                .build();
    }

    public static List<PaymentSystemEntity> paymentSystemDtoToEntityList(List<PaymentSystemDto> paymentSystemDtos){
        if(paymentSystemDtos == null){
            return new ArrayList<>();
        }
        return paymentSystemDtos.stream()
                .map(DtoToEntity::paymentSystemDtoToEntity)
                .collect(Collectors.toList());
    }

    public static List<LimitEntity> limitToDtoList(List<LimitDto> limitDtos){
        if(limitDtos == null){
            return new ArrayList<>();
        }
        return limitDtos.stream()
                .map(DtoToEntity::limitDtoToEntity)
                .collect(Collectors.toList());
    }
}
