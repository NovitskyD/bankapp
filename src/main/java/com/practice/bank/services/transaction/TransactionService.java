package com.practice.bank.services.transaction;

import com.practice.bank.dto.TransactionDto;
import com.practice.bank.dto.TransactionForm;
import com.practice.bank.services.BankService;

public interface TransactionService extends BankService<TransactionDto> {
    TransactionDto processTransaction(TransactionForm transactionForm);
}
