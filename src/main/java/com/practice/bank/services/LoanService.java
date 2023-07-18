package com.practice.bank.services;

import com.practice.bank.dto.LoanDto;
import com.practice.bank.entity.LoanEntity;

import java.util.List;


public interface LoanService extends BankService<LoanDto>{
    LoanEntity getLoanEntityById(String id);
    List<LoanDto> getAllLoans();
    LoanDto createLoanDtoForForm();
    void saveLoanEntity(LoanEntity loanEntity);
}
