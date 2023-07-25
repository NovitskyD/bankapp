package com.practice.bank.Exceptions;

public class ExchangeRateNotFoundException extends RuntimeException{
    public ExchangeRateNotFoundException(String message){
        super(message);
    }
}
