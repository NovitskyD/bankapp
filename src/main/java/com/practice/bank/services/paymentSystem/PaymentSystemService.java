package com.practice.bank.services.paymentSystem;

import com.practice.bank.dto.PaymentSystemDto;
import com.practice.bank.entity.PaymentSystemEntity;
import com.practice.bank.services.BankService;

import java.util.List;

public interface PaymentSystemService extends BankService<PaymentSystemDto> {
    List<PaymentSystemDto> getAllPaymentSystems();
    PaymentSystemEntity getPaymentSystemEntityById(String id);
}
