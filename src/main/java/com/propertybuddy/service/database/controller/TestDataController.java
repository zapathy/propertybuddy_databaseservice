package com.propertybuddy.service.database.controller;

import com.propertybuddy.service.database.model.Price;
import com.propertybuddy.service.database.model.Property;
import com.propertybuddy.service.database.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

@RestController
@RequestMapping("/testdata")
@CrossOrigin
public class TestDataController {

    @Autowired
    PropertyService propertyService;

    @PostMapping("/createall")
    public ResponseEntity<String> createTestData() {
        propertyService.add(
                Property.builder()
                        .district(9)
                        .street("Mester utca")
                        .priceHistory(Arrays.asList(
                                Price.builder()
                                        .date(LocalDate.of(2018,6,13))
                                        .price(new BigDecimal(25000))
                                        .build(),
                                Price.builder()
                                        .date(LocalDate.of(2018,9,21))
                                        .price(new BigDecimal(29000))
                                        .build(),
                                Price.builder()
                                        .date(LocalDate.of(2019,1,5))
                                        .price(new BigDecimal(31500))
                                        .build()
                        ))
                        .build()
        );

        return new ResponseEntity<>("Added test data", new HttpHeaders(), HttpStatus.OK);
    }

}
