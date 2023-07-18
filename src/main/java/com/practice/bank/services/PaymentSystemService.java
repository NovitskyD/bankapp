package com.practice.bank.services;

import com.practice.bank.dto.PaymentSystemDto;
import com.practice.bank.entity.PaymentSystemEntity;

import java.util.List;

public interface PaymentSystemService extends BankService<PaymentSystemDto>{
    List<PaymentSystemDto> getAllPaymentSystems();
    PaymentSystemEntity getPaymentSystemEntityById(String id);
}
