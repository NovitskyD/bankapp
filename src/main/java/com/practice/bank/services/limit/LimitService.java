package com.practice.bank.services.limit;

import com.practice.bank.dto.LimitDto;
import com.practice.bank.entity.LimitEntity;
import com.practice.bank.services.BankService;

import java.util.List;

public interface LimitService extends BankService<LimitDto> {
    List<LimitDto> getAllLimits();
    public LimitEntity getLimitEntityById(String id);
}
