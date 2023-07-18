package com.practice.bank.repository;

import com.practice.bank.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, UUID> {

    @Query("SELECT c FROM CardEntity c WHERE c.account.id = :accountId")
    List<CardEntity> findByAccountId(@Param("accountId") UUID accountId);
    CardEntity findByCardNumber(String cardNumber);
    boolean existsByCardNumber(String cardNumber);
    boolean existsByCvv(String cvv);
    CardEntity findByCardNumberAndCvvAndExpirationDate(String cardNumber, String cvv, LocalDate expirationDate);

    @Query("SELECT c FROM CardEntity c JOIN c.loan l WHERE c.account.accountType = 'Credit'" +
            "AND c.balance < l.loanAmount")
    List<CardEntity> findCreditCardsWithOutstandingLoan();

}
