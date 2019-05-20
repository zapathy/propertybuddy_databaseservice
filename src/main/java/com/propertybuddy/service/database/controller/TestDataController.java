package com.propertybuddy.service.database.controller;

import com.propertybuddy.service.database.model.MessageObject;
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
import java.util.List;

@RestController
@RequestMapping(value = "/testdata")
@CrossOrigin
public class TestDataController {

    @Autowired
    PropertyService propertyService;

    @CrossOrigin
    @GetMapping("/getlatest")
    public ResponseEntity<List<Property>> getLatest(@RequestBody Property p) {
        int propertyInDb = propertyService.isPropertyInDb(p);
        if (propertyInDb == 0)
            return new ResponseEntity<>(null,
                    new HttpHeaders(), HttpStatus.NOT_FOUND);
        else if (propertyInDb > 0){
            System.out.println(propertyService.getLatestPrice(p));
            return new ResponseEntity<>(propertyService.getAllByExample(p),
                    new HttpHeaders(), HttpStatus.OK);
        }

        else
            return new ResponseEntity<>(null,
                    new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
