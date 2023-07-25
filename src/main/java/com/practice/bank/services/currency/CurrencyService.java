package com.practice.bank.services.currency;

import com.practice.bank.dto.CurrencyDto;
import com.practice.bank.entity.CurrencyEntity;
import com.practice.bank.services.BankService;

import java.math.BigDecimal;
import java.util.List;

public interface CurrencyService extends BankService<CurrencyDto> {
    List<CurrencyDto> getAllCurrencies();
    BigDecimal getExchangeRate(String sourceCurrencyCode, String targetCurrencyCode);
    CurrencyEntity getCurrencyEntityById(String id);
}
