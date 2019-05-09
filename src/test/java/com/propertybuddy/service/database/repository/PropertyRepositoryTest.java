package com.propertybuddy.service.database.repository;

import com.propertybuddy.service.database.model.Price;
import com.propertybuddy.service.database.model.Property;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PropertyRepositoryTest {

    @Autowired
    PropertyRepository propertyRepository;
    
    @Test
    public void testIsCanAddAndRemove() {
        assertEquals(0, propertyRepository.findAll().size());
        Price p1 = Price.builder().price(new BigDecimal(20000)).date(LocalDate.of(2018,6,13)).build();
        Price p2 = Price.builder().price(new BigDecimal(25000)).date(LocalDate.of(2018,7,21)).build();
        Price p3 = Price.builder().price(new BigDecimal(29000)).date(LocalDate.of(2019,1,5)).build();
        Property a = Property.builder().district(9).priceHistory(Arrays.asList(p1,p2,p3)).build();
        propertyRepository.save(a);
        assertEquals(1, propertyRepository.findAll().size());
        propertyRepository.delete(a);
        assertEquals(0, propertyRepository.findAll().size());
    }
}