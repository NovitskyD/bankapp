package com.practice.bank.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cards")
public class CardEntity {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private AccountEntity account;

    @Column(name = "card_number")
    @NonNull private String cardNumber;

    @Column(name = "expiration_date")
    LocalDate expirationDate;

    @Column(name = "holder_name")
    String holderName;

    @Column(name = "cvv")
    String cvv;

    @Column(name = "status")
    String status;
}
