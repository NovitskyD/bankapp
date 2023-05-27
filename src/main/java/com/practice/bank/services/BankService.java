package com.practice.bank.services;

public interface BankService<T> {
    T getDataById(String id);
    void insertData(T element);
    void updateData(T element);

}
