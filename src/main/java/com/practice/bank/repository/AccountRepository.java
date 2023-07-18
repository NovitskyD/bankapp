package com.practice.bank.repository;

import com.practice.bank.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {

    @Query("SELECT acc FROM AccountEntity acc WHERE acc.client.id = :clientId")
    List<AccountEntity> findByClientId(@Param("clientId") UUID clientId);

    boolean existsByAccountNumber(String accountNumber);
}
