package com.practice.bank.helpers;

import com.practice.bank.repository.AccountRepository;

public class AccountNumberGenerator implements NumberGenerator{
    private static final int ACCOUNT_NUMBER_LENGTH = 10;
    private final AccountRepository accountRepository;

    public AccountNumberGenerator(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public String generateNumber() {
        String accountNumber;

        do{
            accountNumber = RandomNumberGenerator.generateNumber(ACCOUNT_NUMBER_LENGTH);
        } while (!isAccountNumberUnique(accountNumber));

        return "A" + accountNumber;
    }

    private boolean isAccountNumberUnique(String accountNumber){
        return !accountRepository.existsByAccountNumber(accountNumber);
    }
}
