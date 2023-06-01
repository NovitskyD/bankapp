package com.practice.bank.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "clients")
public class ClientEntity {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(name = "first_name")
    @NonNull private String firstName;

    @Column(name = "last_name")
    @NonNull private String lastName;

    @Column(name = "birth_Date")
    @Past
    @NonNull private Instant birthDate;

    @Column(name = "address")
    @NonNull private String address;

    @Column(name = "phone")
    @NonNull private String phone;

    @Column(name = "email")
    @Email(message = "Email should be valid")
    private String email;

    @Column(name = "password")
    @NonNull private String password;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccountEntity> accounts;
}
