package com.propertybuddy.service.database.service;

import com.propertybuddy.service.database.model.Property;
import com.propertybuddy.service.database.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

    @Autowired
    PropertyRepository repository;

    public void add(Property p) {
        repository.save(p);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
