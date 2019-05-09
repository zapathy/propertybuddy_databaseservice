package com.propertybuddy.service.database.controller;

import com.propertybuddy.service.database.model.MessageObject;
import com.propertybuddy.service.database.model.Property;
import com.propertybuddy.service.database.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/properties")
@CrossOrigin
public class PropertyController {
    @Autowired
    PropertyService propertyService;

    Logger logger = LoggerFactory.getLogger(PropertyController.class);

    @DeleteMapping("")
    public ResponseEntity<MessageObject> deleteEverything() {
        propertyService.deleteAll();
        return new ResponseEntity<>(new MessageObject("Empty Property database successful"), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Property>> returnEverything() {
        return new ResponseEntity<>(propertyService.getAll(), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<MessageObject> addProperty(@RequestBody Property newProperty) {
        logger.info(newProperty.toString());
        propertyService.add(newProperty);
        return new ResponseEntity<>(new MessageObject("Added property successful"), new HttpHeaders(), HttpStatus.OK);
    }
}
