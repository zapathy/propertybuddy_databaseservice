package com.propertybuddy.service.database.service;

import com.propertybuddy.service.database.model.Price;
import com.propertybuddy.service.database.model.Property;
import com.propertybuddy.service.database.repository.PropertyRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class PropertyService {

    private final
    PropertyRepository repository;

    public PropertyService(PropertyRepository repository) {
        this.repository = repository;
    }

    public void save(Property p) {
        repository.save(p);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public List<Property> getAll() {
        return repository.findAll();
    }

    public int isPropertyInDb(Property p) {
        List<Property> properties = repository.findAll(Example.of(p));
        if (properties.size() == 0) return 0;
        else if (properties.size() == 1) return 1;
        else {
            return 2;
        }
    }

    public Price getLatestPrice(Property property) {
        List<Property> properties = getAllByExample(property);
        properties.forEach(o -> o.getPricehistory().sort(Comparator.comparing(Price::getDatetime)));
        return properties.get(0).getPricehistory().get(0);
    }

    public List<Property> getAllByExample(Property p) {
        return repository.findAll(Example.of(p));
    }
}
