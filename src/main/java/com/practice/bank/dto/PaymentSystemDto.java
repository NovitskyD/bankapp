package com.practice.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class PaymentSystemDto {
    private String id;
    @NonNull private String name;
    private String description;
    private String logoUrl;
    private BigDecimal transactionFeePercentage;
    private List<PaymentSystemCountryDto> countries;
    public PaymentSystemDto() {
        this.countries = new ArrayList<>();
    }

    public void setCountry(CountryDto country) {
        PaymentSystemCountryDto paymentSystemCountry = PaymentSystemCountryDto.builder()
                .id(new PaymentSystemCountryIdDto(this.getId(), country.getId()))
                .paymentSystem(this)
                .country(country)
                .build();

        this.getCountries().add(paymentSystemCountry);
    }
}
