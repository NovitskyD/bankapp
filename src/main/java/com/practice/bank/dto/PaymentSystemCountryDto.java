package com.practice.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentSystemCountryDto {
    private PaymentSystemCountryIdDto id;
    private PaymentSystemDto paymentSystem;
    private CountryDto country;
}
