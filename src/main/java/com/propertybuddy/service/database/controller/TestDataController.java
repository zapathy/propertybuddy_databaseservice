package com.propertybuddy.service.database.controller;

import com.propertybuddy.service.database.model.MessageObject;
import com.propertybuddy.service.database.model.Price;
import com.propertybuddy.service.database.model.Property;
import com.propertybuddy.service.database.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

@RestController
@RequestMapping(value = "/testdata")
@CrossOrigin
public class TestDataController {

    @Autowired
    PropertyService propertyService;

    @CrossOrigin
    @PostMapping("/createall")
    public ResponseEntity<MessageObject> createTestData() {
        propertyService.deleteAll();
//        propertyService.save(
//                Property.builder()
//                        .district(9)
//                        .priceHistory(Arrays.asList(
//                                Price.builder()
//                                        .date(LocalDate.of(2018,6,13))
//                                        .price(new BigDecimal(25000))
//                                        .build(),
//                                Price.builder()
//                                        .date(LocalDate.of(2018,9,21))
//                                        .price(new BigDecimal(29000))
//                                        .build(),
//                                Price.builder()
//                                        .date(LocalDate.of(2019,1,5))
//                                        .price(new BigDecimal(31500))
//                                        .build()
//                        ))
//                        .build()
//        );
//        propertyService.save(
//                Property.builder()
//                        .district(7)
//                        .priceHistory(Arrays.asList(
//                                Price.builder()
//                                        .date(LocalDate.of(2018,6,13))
//                                        .price(new BigDecimal(50000))
//                                        .build(),
//                                Price.builder()
//                                        .date(LocalDate.of(2018,9,21))
//                                        .price(new BigDecimal(55000))
//                                        .build(),
//                                Price.builder()
//                                        .date(LocalDate.of(2019,1,5))
//                                        .price(new BigDecimal(54000))
//                                        .build()
//                        ))
//                        .build()
//        );

        return new ResponseEntity<>(new MessageObject("Added test data"), new HttpHeaders(), HttpStatus.OK);
    }

}
