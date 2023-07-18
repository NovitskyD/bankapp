package com.practice.bank.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "accounts")
public class AccountEntity {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private ClientEntity client;

    @Column(name = "account_number")
    @NonNull private String accountNumber;

    @Column(name = "account_type")
    @NonNull private String accountType;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CardEntity> cards;
}
