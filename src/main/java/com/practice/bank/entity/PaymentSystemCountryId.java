package com.practice.bank.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class PaymentSystemCountryId implements Serializable {
    private Long paymentSystemId;
    private Long countryId;
}
