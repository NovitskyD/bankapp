package com.practice.bank.services;

import com.practice.bank.Exceptions.CardNotFoundException;
import com.practice.bank.dto.AccountDto;
import com.practice.bank.dto.CardDto;
import com.practice.bank.dto.CurrencyDto;
import com.practice.bank.dto.TopUpDto;
import com.practice.bank.entity.*;
import com.practice.bank.helpers.CVVGenerator;
import com.practice.bank.helpers.CardNumberGenerator;
import com.practice.bank.helpers.NumberGenerator;
import com.practice.bank.helpers.NumberGeneratorFactory;
import com.practice.bank.mapper.DtoToEntity;
import com.practice.bank.mapper.EntityMapper;
import com.practice.bank.mapper.EntityToDto;
import com.practice.bank.repository.CardRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CardServiceImpl implements CardService{
    private final CardRepository cardRepository;
    private final AccountService accountService;
    private final CurrencyService currencyService;
    private final LimitService limitService;
    private final LoanService loanService;
    private final PaymentSystemService paymentSystemService;
    private final NumberGeneratorFactory numberGeneratorFactory;
    private final EntityMapper entityMapper;

    @Override
    public CardDto getDataById(String id) {
        CardEntity cardEntity = cardRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntityNotFoundException("Card was not found"));

        return EntityToDto.cardsEntityToDto(cardEntity);
    }

    @Override
    public void insertData(CardDto element) {
        AccountEntity accountEntity = getAccountEntity(element.getAccount().getId());
        CardEntity cardEntity = DtoToEntity.cardsDtoToEntity(element);
        cardEntity.setAccount(accountEntity);

        LimitEntity limitEntity = getLimitEntity(element.getLimit().getId());
        cardEntity.setLimit(limitEntity);
        limitEntity.getCards().add(cardEntity);

        CurrencyEntity currency = getCurrencyEntity(element.getCurrency().getId());
        cardEntity.setCurrency(currency);

        PaymentSystemEntity paymentSystemEntity = getPaymentSystemEntity(element.getPaymentSystem().getId());
        cardEntity.setPaymentSystem(paymentSystemEntity);
        accountEntity.getCards().add(cardEntity);

        if("Credit".equals(cardEntity.getAccount().getAccountType()) && element.getLoan() != null){
            LoanEntity loanEntity = DtoToEntity.loansDtoToEntity(element.getLoan());
            loanEntity.setCard(cardEntity);
            loanService.saveLoanEntity(loanEntity);
            cardEntity.setLoan(loanEntity);
            cardEntity.setBalance(loanEntity.getLoanAmount());
        }
        cardRepository.save(cardEntity);
    }

    @Override
    public void updateData(CardDto element) {
        UUID id = UUID.fromString(element.getId());
        CardEntity existingCard = cardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Card was not found"));

        entityMapper.INSTANCE.mapCardEntityToExistingCardEntity(existingCard,
                DtoToEntity.cardsDtoToEntity(element));

        cardRepository.save(existingCard);
    }

    @Override
    public List<CardDto> getCardsByAccountId(String accountId) {
        List<CardEntity> cardEntities = cardRepository.findByAccountId(UUID.fromString(accountId));
        return EntityToDto.cardsEntitiesToDtoList(cardEntities);
    }

    @Override
    public CardDto createCardDtoForForm(String accountId){
        AccountDto accountDto = accountService.getDataById(accountId);

        System.out.println("account Dto: " + accountDto);
        return CardDto.builder()
                .account(accountDto)
                .holderName(accountDto.getClient().getFirstName() + " " + accountDto.getClient().getLastName())
                .cardNumber(generateCardNumberForCardDto())
                .cvv(generateCVVForCardDto())
                .balance(BigDecimal.valueOf(0))
                .status("Active")
                .expirationDate(LocalDate.now().plusYears(4))
                .build();
    }

    private String generateCardNumberForCardDto(){
        NumberGenerator cardNumberGenerator = numberGeneratorFactory.getGenerator(CardNumberGenerator.class);
        return cardNumberGenerator.generateNumber();
    }

    private String generateCVVForCardDto(){
        NumberGenerator cvvGenerator = numberGeneratorFactory.getGenerator(CVVGenerator.class);
        return cvvGenerator.generateNumber();
    }

    @Override
    public void topUpBalance(TopUpDto topUpDto){
        String cardNumber = topUpDto.getCardNumber();
        BigDecimal amount = topUpDto.getAmount();
        CurrencyDto currency = topUpDto.getCurrency();

        CardEntity cardEntity = cardRepository.findByCardNumber(cardNumber);
        CurrencyDto currencyDto = currencyService.getDataById(currency.getId());
        if(cardEntity == null){
            throw new CardNotFoundException("Card not found");
        }
        BigDecimal currentBalance = cardEntity.getBalance();
        BigDecimal newBalance;

        if(!currencyDto.getCode().equals(cardEntity.getCurrency().getCode())){
            BigDecimal exchangeRate = currencyService.getExchangeRate(currencyDto.getCode(), cardEntity.getCurrency().getCode());
            BigDecimal baseAmount = amount.multiply(exchangeRate);
            newBalance = currentBalance.add(baseAmount);
        }
        else {
            newBalance = currentBalance.add(amount);
        }
        cardEntity.setBalance(newBalance);
        cardRepository.save(cardEntity);
    }

    @Override
    public List<CardDto> getAllCardsForClient(String clientId) {
        List<AccountDto> accounts = accountService.getAccountsByClientId(clientId);
        if (accounts == null) {
            return Collections.emptyList();
        }

        return accounts.stream()
                .flatMap(account -> account.getCards().stream())
                .collect(Collectors.toList());
    }

    @Override
    public CardDto getCardByCardNumberAndCvvAndExpirationDate(String cardNumber, String cvv, LocalDate expirationDate) {
        CardEntity cardEntity = cardRepository.findByCardNumberAndCvvAndExpirationDate(cardNumber, cvv, expirationDate);
        return EntityToDto.cardsEntityToDto(cardEntity);
    }

    @Override
    public CardDto getCardByCardNumber(String cardNumber) {
        CardEntity cardEntity = cardRepository.findByCardNumber(cardNumber);
        return EntityToDto.cardsEntityToDto(cardEntity);
    }

    private AccountEntity getAccountEntity(String accountId) {
        return accountService.getAccountEntityById(accountId);
    }

    private LimitEntity getLimitEntity(String limitId) {
        return limitService.getLimitEntityById(limitId);
    }

    private CurrencyEntity getCurrencyEntity(String currencyId) {
        return currencyService.getCurrencyEntityById(currencyId);
    }

    private PaymentSystemEntity getPaymentSystemEntity(String paymentSystemId) {
        return paymentSystemService.getPaymentSystemEntityById(paymentSystemId);
    }
}
