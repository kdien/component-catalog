package com.oultoncollege.ComponentCatalog.services;

import com.oultoncollege.ComponentCatalog.models.Component;
import com.oultoncollege.ComponentCatalog.models.Language;
import com.oultoncollege.ComponentCatalog.repositories.ComponentRepository;
import com.oultoncollege.ComponentCatalog.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;
    private final ComponentRepository componentRepository;

    @Autowired
    public LanguageServiceImpl(LanguageRepository languageRepository, ComponentRepository componentRepository) {
        this.languageRepository = languageRepository;
        this.componentRepository = componentRepository;
    }

    @Override
    public Language createLanguage(Language language) {
        return languageRepository.save(language);
    }

    @Override
    public Language getLanguage(int id) {
        if (languageRepository.findById(id).isPresent()) {
            return languageRepository.findById(id).get();
        }

        return null;
    }

    @Override
    public Language editLanguage(Language language) {
        return languageRepository.save(language);
    }

    @Override
    public void deleteLanguage(Language language) {
        List<Component> components = language.getComponents();

        for (Component component : components) {
            componentRepository.delete(component);
        }

        language.setComponents(null);

        languageRepository.delete(language);
    }

    @Override
    public void deleteLanguage(int id) {
        Language language = getLanguage(id);
        List<Component> components = language.getComponents();

        for (Component component : components) {
            componentRepository.delete(component);
        }

        languageRepository.deleteById(id);
    }

    @Override
    public List<Language> getAllLanguages() {
        return languageRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
    }

    @Override
    public long countLanguages() {
        return languageRepository.count();
    }
}
