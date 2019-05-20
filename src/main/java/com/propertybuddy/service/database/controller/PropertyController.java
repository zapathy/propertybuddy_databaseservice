package com.propertybuddy.service.database.controller;

import com.propertybuddy.service.database.model.MessageObject;
import com.propertybuddy.service.database.model.Price;
import com.propertybuddy.service.database.model.Property;
import com.propertybuddy.service.database.model.PropertyInput;
import com.propertybuddy.service.database.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/properties")
@CrossOrigin
public class PropertyController {
    @Autowired
    PropertyService propertyService;

    private Logger logger = LoggerFactory.getLogger(PropertyController.class);

    @DeleteMapping("")
    public ResponseEntity<MessageObject> deleteEverything() {
        propertyService.deleteAll();
        return new ResponseEntity<>(new MessageObject("Empty Property database successful"), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<MessageObject> addProperty(@RequestBody PropertyInput newPropertyInput) {
        Property newProperty = new Property(newPropertyInput);
        Price newPrice = new Price(newPropertyInput);
        int propertyInDb = propertyService.isPropertyInDb(newProperty);
        if (propertyInDb == 1) {
            Property oldProperty = propertyService.getAllByExample(newProperty).get(0);
            List<Price> priceHistory = oldProperty.getPricehistory();
            boolean alreadyHaveRecentData = false;
            for (Price p : priceHistory) {
                Long d = Math.abs(Duration.between(p.getDatetime(), newPrice.getDatetime()).toMinutes());
                if (d <= 30) alreadyHaveRecentData = true;
                break;
            }
            if (!alreadyHaveRecentData) {
                priceHistory.add(newPrice);
                propertyService.save(oldProperty);
                return new ResponseEntity<>(new MessageObject("Added price to exsiting property"),
                        new HttpHeaders(), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(new MessageObject("Price not added because already a price for this date"),
                        new HttpHeaders(), HttpStatus.ACCEPTED);
            }

        } else if (propertyInDb == 0) {
            newProperty.getPricehistory().add(newPrice);
            propertyService.save(newProperty);
            return new ResponseEntity<>(new MessageObject("Added property successful"),
                    new HttpHeaders(), HttpStatus.CREATED);
        } else if (propertyInDb == 2)
            return new ResponseEntity<>(new MessageObject("Well defined Property should not have duplicates..."),
                    new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        else
            return new ResponseEntity<>(new MessageObject("This should not be reachable ..."),
                    new HttpHeaders(), HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping("")
    public ResponseEntity<List<Property>> getProperty(@RequestBody Property p) {
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

    @GetMapping("/all")
    public ResponseEntity<List<Property>> getAllProperty() {
        return new ResponseEntity<>(propertyService.getAll(),
                new HttpHeaders(), HttpStatus.OK);
    }

}
