package com.propertybuddy.service.database.repository;

import com.propertybuddy.service.database.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository  extends JpaRepository<Property, Long> {
}
