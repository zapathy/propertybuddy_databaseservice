package com.propertybuddy.service.database.repository;

import com.propertybuddy.service.database.model.Price;
import com.propertybuddy.service.database.model.Property;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PropertyRepositoryTest {

    @Autowired
    PropertyRepository propertyRepository;
    
    @Test
    public void testIsCanAddAndRemove() {

    }
}