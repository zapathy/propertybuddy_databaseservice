package com.propertybuddy.service.database.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Property {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Integer district;
    private Integer bedrooms;
    private Integer size;
    private String floor;
    private Boolean garage;
    private Boolean parkingplace;
    private String view;
    private Boolean furnished;
    private Boolean elevator;
    private Boolean airconditioned;
    private BigInteger pricehuf;
    private BigInteger priceeur;
    private String streetsuffix;
    private String streetname;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Price> priceHistory;

}
