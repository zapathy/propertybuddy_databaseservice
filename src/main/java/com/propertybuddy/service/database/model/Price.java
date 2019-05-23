package com.propertybuddy.service.database.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "Price")
@Table(name = "price")
public class Price {
    public Price(PropertyInput input) {
        this.pricehuf = input.getPricehuf();
        this.priceeur = input.getPriceeur();
        this.datetime = LocalDateTime.now();
    }

    @Id
    @GeneratedValue
    private Long id;

    private BigInteger pricehuf;
    private BigInteger priceeur;

    private LocalDateTime datetime;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    @JoinColumn(name = "property_id")
    private Property property;
}
