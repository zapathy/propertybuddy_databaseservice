package com.propertybuddy.service.database.repository;

import com.propertybuddy.service.database.model.Price;
import com.propertybuddy.service.database.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository  extends JpaRepository<Property, Long> {
}
