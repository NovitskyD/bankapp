package com.practice.bank.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "transactions")
public class TransactionEntity {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "sender_card_id", referencedColumnName = "id")
    private CardEntity senderCard;

    @ManyToOne
    @JoinColumn(name = "recipient_card_id", referencedColumnName = "id")
    private CardEntity recipientCard;

    @Column(name = "type")
    @NonNull private String type;

    @Column(name = "amount")
    @NonNull private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    @NonNull private CurrencyEntity currency;

    @Column(name = "description")
    private String description;

    @Column(name = "timestamp")
    @NonNull private String timestamp;

    @Column(name = "status")
    @NonNull private String status;
}
