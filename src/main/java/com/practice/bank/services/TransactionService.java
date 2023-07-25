package com.practice.bank.services;

import com.practice.bank.dto.TransactionDto;
import com.practice.bank.dto.TransactionForm;

public interface TransactionService extends BankService<TransactionDto>{
    TransactionDto processTransaction(TransactionForm transactionForm);
}
