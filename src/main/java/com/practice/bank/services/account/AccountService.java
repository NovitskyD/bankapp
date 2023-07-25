package com.practice.bank.services.account;

import com.practice.bank.dto.AccountDto;
import com.practice.bank.entity.AccountEntity;
import com.practice.bank.services.BankService;

import java.util.List;

public interface AccountService extends BankService<AccountDto> {
    List<AccountDto> getAccountsByClientId(String clientId);
    AccountDto createAccountDtoForForm(String clientId);
    public AccountEntity getAccountEntityById(String id);
}
