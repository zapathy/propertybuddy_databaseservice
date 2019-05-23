package com.propertybuddy.service.database.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyInput {

    private Long id;
    private String name;
    private Integer district;
    private Integer bedrooms;
    private Integer size;
    private String floor;
    private Boolean garage;
    private String view;
    private String streetsuffix;
    private String streetname;
    private BigInteger pricehuf;
    private BigInteger priceeur;

}
