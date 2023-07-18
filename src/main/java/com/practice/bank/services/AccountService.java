package com.practice.bank.services;

import com.practice.bank.dto.AccountDto;
import com.practice.bank.entity.AccountEntity;

import java.util.List;

public interface AccountService extends BankService<AccountDto>{
    List<AccountDto> getAccountsByClientId(String clientId);
    AccountDto createAccountDtoForForm(String clientId);
    public AccountEntity getAccountEntityById(String id);
}
