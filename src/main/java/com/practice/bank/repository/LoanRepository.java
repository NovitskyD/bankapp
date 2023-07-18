package com.practice.bank.repository;

import com.practice.bank.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LoanRepository extends JpaRepository<LoanEntity, UUID> {
}
