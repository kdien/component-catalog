package com.oultoncollege.ComponentCatalog.services;

import com.oultoncollege.ComponentCatalog.models.Component;
import java.util.List;

public interface ComponentService {

    Component createComponent(Component component);

    Component getComponent(int id);

    Component editComponent(Component component);

    void deleteComponent(Component component);

    void deleteComponent(int id);

    List<Component> getAllComponents();

    long countComponents();
}
