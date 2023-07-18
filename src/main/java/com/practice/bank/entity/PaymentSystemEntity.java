package com.practice.bank.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name = "payment_systems")
public class PaymentSystemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    @NonNull private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(name = "transaction_fee_percentage")
    private BigDecimal transactionFeePercentage;

    @OneToMany(mappedBy = "paymentSystem", cascade = CascadeType.ALL)
    private List<PaymentSystemCountryEntity> countries;

    public PaymentSystemEntity() {
        this.countries = new ArrayList<>();
    }

    public void setCountry(CountryEntity country) {
        PaymentSystemCountryEntity paymentSystemCountry = PaymentSystemCountryEntity.builder()
                .id(new PaymentSystemCountryId(this.getId(), country.getId()))
                .paymentSystem(this)
                .country(country)
                .build();

        this.getCountries().add(paymentSystemCountry);
    }
}
