package com.practice.bank.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanDto {
    private String id;
    private AccountDto account;
    @NonNull private BigDecimal loanAmount;
    @NonNull private BigDecimal interestRate;
    @NonNull private Integer termMonths;
    @NonNull private LocalDate startDate;
    private LocalDate endDate;
    private String status;
}
