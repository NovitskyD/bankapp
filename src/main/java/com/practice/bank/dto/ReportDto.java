package com.practice.bank.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportDto {
    private String id;
    private AccountDto account;
    private String title;
    @NonNull private String description;
    @NonNull private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
