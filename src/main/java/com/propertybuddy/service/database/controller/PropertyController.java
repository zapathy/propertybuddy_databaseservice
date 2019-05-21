package com.propertybuddy.service.database.controller;

import com.propertybuddy.service.database.model.MessageObject;
import com.propertybuddy.service.database.model.Price;
import com.propertybuddy.service.database.model.Property;
import com.propertybuddy.service.database.model.PropertyInput;
import com.propertybuddy.service.database.service.PropertyService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/properties")
@CrossOrigin
public class PropertyController {
    private final
    PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

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
            Price latestPrice = propertyService.getLatestPrice(oldProperty);
            long deltaTime = Math.abs(Duration.between(latestPrice.getDatetime(), LocalDateTime.now()).toHours());
            if (deltaTime <= 48) {
                if (!latestPrice.getPricehuf().equals(newPrice.getPricehuf())) {
                    oldProperty.getPricehistory().add(newPrice);
                    propertyService.save(oldProperty);
                    return new ResponseEntity<>(new MessageObject("Added price because changed since last price"),
                            new HttpHeaders(), HttpStatus.CREATED);
                } else {
                    return new ResponseEntity<>(new MessageObject("Price not added because too close to latest price"),
                            new HttpHeaders(), HttpStatus.ACCEPTED);
                }
            } else {
                oldProperty.getPricehistory().add(newPrice);
                propertyService.save(oldProperty);
                return new ResponseEntity<>(new MessageObject("Added price to existing property"),
                        new HttpHeaders(), HttpStatus.CREATED);
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
        else if (propertyInDb > 0) {
            return new ResponseEntity<>(propertyService.getAllByExample(p),
                    new HttpHeaders(), HttpStatus.OK);
        } else
            return new ResponseEntity<>(null,
                    new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Property>> getAllProperty() {
        return new ResponseEntity<>(propertyService.getAll(),
                new HttpHeaders(), HttpStatus.OK);
    }

}
