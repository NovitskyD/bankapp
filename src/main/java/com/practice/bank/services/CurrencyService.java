package com.practice.bank.services;

import com.practice.bank.dto.CurrencyDto;
import com.practice.bank.entity.CurrencyEntity;

import java.math.BigDecimal;
import java.util.List;

public interface CurrencyService extends BankService<CurrencyDto>{
    List<CurrencyDto> getAllCurrencies();
    BigDecimal getExchangeRate(String sourceCurrencyCode, String targetCurrencyCode);
    CurrencyEntity getCurrencyEntityById(String id);
}
