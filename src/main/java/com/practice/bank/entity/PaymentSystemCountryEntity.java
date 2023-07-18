package com.practice.bank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "payment_system_countries")
public class PaymentSystemCountryEntity {
    @EmbeddedId
    private PaymentSystemCountryId id;

    @ManyToOne
    @MapsId("paymentSystemId")
    @JoinColumn(name="payment_system_id")
    private PaymentSystemEntity paymentSystem;

    @ManyToOne
    @MapsId("countryId")
    @JoinColumn(name = "country_id")
    private CountryEntity country;
}
