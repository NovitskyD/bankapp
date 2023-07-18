package com.practice.bank.services;

import com.practice.bank.Exceptions.ExchangeRateNotFoundException;
import com.practice.bank.dto.CurrencyDto;
import com.practice.bank.entity.CurrencyEntity;
import com.practice.bank.entity.CurrencyRateEntity;
import com.practice.bank.mapper.EntityToDto;
import com.practice.bank.repository.CurrencyRateRepository;
import com.practice.bank.repository.CurrencyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService{
    private final CurrencyRepository currencyRepository;
    private final CurrencyRateRepository currencyRateRepository;

    @Override
    public CurrencyDto getDataById(String id) {
        return EntityToDto.currencyEntityToDto(currencyRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException("Currency was not found")));
    }

    @Override
    public CurrencyEntity getCurrencyEntityById(String id) {
        return currencyRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException("Currency was not found"));
    }

    @Override
    public void insertData(CurrencyDto element) {

    }

    @Override
    public void updateData(CurrencyDto element) {

    }

    @Override
    public List<CurrencyDto> getAllCurrencies() {
        List<CurrencyEntity> currencyEntities = currencyRepository.findAll();
        return EntityToDto.currencyEntityToDtoList(currencyEntities);
    }

    @Override
    public BigDecimal getExchangeRate(String sourceCurrencyCode, String targetCurrencyCode){
        CurrencyRateEntity rateEntity = currencyRateRepository.findByBaseCurrencyCodeAndTargetCurrencyCode(sourceCurrencyCode, targetCurrencyCode);

        if (rateEntity == null){
            throw new ExchangeRateNotFoundException("Exchange rate not found");
        }
        return rateEntity.getRate();
    }
}
