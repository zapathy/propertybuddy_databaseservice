package com.propertybuddy.service.database.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigInteger;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Price {
    public Price(PropertyInput input) {
        this.pricehuf = input.getPricehuf();
        this.priceeur = input.getPriceeur();
        this.date = LocalDate.now();
    }

    @Id
    @GeneratedValue
    private Long id;

    private BigInteger pricehuf;
    private BigInteger priceeur;

    private LocalDate date;
}
