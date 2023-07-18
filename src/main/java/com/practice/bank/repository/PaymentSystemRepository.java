package com.practice.bank.repository;

import com.practice.bank.entity.PaymentSystemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentSystemRepository extends JpaRepository<PaymentSystemEntity, Long> {
}
