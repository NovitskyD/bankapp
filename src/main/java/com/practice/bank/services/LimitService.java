package com.practice.bank.services;

import com.practice.bank.dto.LimitDto;
import com.practice.bank.entity.LimitEntity;

import java.util.List;

public interface LimitService extends BankService<LimitDto>{
    List<LimitDto> getAllLimits();
    public LimitEntity getLimitEntityById(String id);
}
