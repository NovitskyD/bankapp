package com.practice.bank.services;

import com.practice.bank.dto.LoanDto;
import com.practice.bank.entity.LoanEntity;
import com.practice.bank.mapper.EntityToDto;
import com.practice.bank.repository.LoanRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService{
    private final LoanRepository loanRepository;
    @Override
    public LoanDto getDataById(String id) {
        return EntityToDto.loansEntityToDto(loanRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntityNotFoundException("Loan was not found")));
    }

    @Override
    public LoanEntity getLoanEntityById(String id) {
        return loanRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntityNotFoundException("Loan was not found"));
    }

    @Override
    public void insertData(LoanDto element) {

    }

    @Override
    public void updateData(LoanDto element) {

    }

    @Override
    public List<LoanDto> getAllLoans() {
        List<LoanEntity> loanEntities = loanRepository.findAll();
        return EntityToDto.loansEntityToDtoList(loanEntities);
    }

    @Override
    public LoanDto createLoanDtoForForm(){
        return LoanDto.builder()
                .loanAmount(BigDecimal.valueOf(0))
                .interestRate(BigDecimal.valueOf(0))
                .startDate(LocalDate.now())
                .status("Active")
                .build();
    }

    @Override
    public void saveLoanEntity(LoanEntity loanEntity){
        loanRepository.save(loanEntity);
    }
}
