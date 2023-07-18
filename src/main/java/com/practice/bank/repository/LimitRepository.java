package com.practice.bank.repository;

import com.practice.bank.entity.LimitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LimitRepository extends JpaRepository<LimitEntity, UUID> {

}
