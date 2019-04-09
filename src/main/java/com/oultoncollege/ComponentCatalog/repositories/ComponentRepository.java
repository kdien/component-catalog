package com.oultoncollege.ComponentCatalog.repositories;

import com.oultoncollege.ComponentCatalog.models.Component;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComponentRepository extends JpaRepository<Component, Integer> {

}
