package com.propertybuddy.service.database.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class WelcomeController {
    @GetMapping("")
    public String welcomeMessage() {
        return "Welcome to Property Buddy!";
    }

}