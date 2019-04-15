package com.oultoncollege.ComponentCatalog.services;

import com.oultoncollege.ComponentCatalog.models.Component;
import com.oultoncollege.ComponentCatalog.repositories.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ComponentServiceImpl implements ComponentService {

    private final ComponentRepository componentRepository;

    @Autowired
    public ComponentServiceImpl(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    @Override
    public Component createComponent(Component component) {
        return componentRepository.save(component);
    }

    @Override
    public Component getComponent(int id) {
        if(componentRepository.findById(id).isPresent()) {
            return componentRepository.findById(id).get();
        }

        return null;
    }

    @Override
    public Component editComponent(Component component) {
        return componentRepository.save(component);
    }

    @Override
    public void deleteComponent(Component component) {
        componentRepository.delete(component);
    }

    @Override
    public void deleteComponent(int id) {
        componentRepository.deleteById(id);
    }

    @Override
    public List<Component> getAllComponents() {
        return componentRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
    }

    @Override
    public long countComponents() {
        return componentRepository.count();
    }
}
