package com.practice.bank.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    @OneToOne(mappedBy = "card")
    private LoanEntity loan;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "holder_name")
    private String holderName;

    @Column(name = "cvv")
    private String cvv;

    @Column(name = "balance")
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    @NonNull private CurrencyEntity currency;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "limit_id", referencedColumnName = "id")
    private LimitEntity limit;

    @ManyToOne
    @JoinColumn(name = "payment_system_id", referencedColumnName = "id")
    private PaymentSystemEntity paymentSystem;

    public void setBalance(BigDecimal balance) {
        this.balance = balance.setScale(2, RoundingMode.HALF_EVEN);
    }
}
