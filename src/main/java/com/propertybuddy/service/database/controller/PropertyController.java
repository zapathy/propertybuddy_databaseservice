package com.propertybuddy.service.database.controller;

import com.propertybuddy.service.database.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/properties")
@CrossOrigin
public class PropertyController {
    @Autowired
    PropertyService propertyService;

    @DeleteMapping("")
    public ResponseEntity<String> deleteEverything() {
        propertyService.deleteAll();
        return new ResponseEntity<>("Empty Property database successful", new HttpHeaders(), HttpStatus.OK);
    }

}
