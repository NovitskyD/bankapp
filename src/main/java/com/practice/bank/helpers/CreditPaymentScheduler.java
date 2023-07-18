package com.practice.bank.helpers;

import com.practice.bank.services.CreditCardService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CreditPaymentScheduler {
    private final CreditCardService creditCardService;

    public CreditPaymentScheduler(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void processCreditPayments(){
        creditCardService.processCreditPayments();
    }
}
