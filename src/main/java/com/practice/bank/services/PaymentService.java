package com.practice.bank.services;

import com.practice.bank.dto.PaymentDto;

public interface PaymentService {
    void processPayment(PaymentDto paymentDto);
}
