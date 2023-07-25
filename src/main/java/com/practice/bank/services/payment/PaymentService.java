package com.practice.bank.services.payment;

import com.practice.bank.dto.PaymentDto;

public interface PaymentService {
    void processPayment(PaymentDto paymentDto);
}
