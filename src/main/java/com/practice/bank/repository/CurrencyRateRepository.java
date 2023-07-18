package com.practice.bank.repository;

import com.practice.bank.entity.CurrencyRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRateRepository extends JpaRepository<CurrencyRateEntity, Long> {
    CurrencyRateEntity findByBaseCurrencyCodeAndTargetCurrencyCode(String sourceCurrencyCode, String targetCurrencyCode);
}
