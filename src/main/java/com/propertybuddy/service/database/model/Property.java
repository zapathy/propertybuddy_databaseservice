package com.propertybuddy.service.database.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "Property")
@Table(name = "property")
public class Property {
    public Property(PropertyInput input) {
        this.name = input.getName();
        this.district = input.getDistrict();
        this.bedrooms = input.getBedrooms();
        this.size = input.getSize();
        this.floor = input.getFloor();
        this.garage = input.getGarage();
        this.view = input.getView();
        this.streetsuffix = input.getStreetsuffix();
        this.streetname = input.getStreetname();
    }

    @Id
    @GeneratedValue
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

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER,
            mappedBy = "property"
    )
    private List<Price> pricehistory = new ArrayList<>();

    public void addPrice(Price price) {
        pricehistory.add(price);
        price.setProperty(this);
    }

}


