package com.oultoncollege.ComponentCatalog.services;

import com.oultoncollege.ComponentCatalog.models.Language;
import com.oultoncollege.ComponentCatalog.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;

    @Autowired
    public LanguageServiceImpl(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public Language createComponent(Language language) {
        return languageRepository.save(language);
    }

    @Override
    public Language getComponent(int id) {
        if(languageRepository.findById(id).isPresent()) {
            return languageRepository.findById(id).get();
        }

        return null;
    }

    @Override
    public Language editComponent(Language language) {
        return languageRepository.save(language);
    }

    @Override
    public void deleteComponent(Language language) {
        languageRepository.delete(language);
    }

    @Override
    public void deleteComponent(int id) {
        languageRepository.deleteById(id);
    }

    @Override
    public List<Language> getAllComponents() {
        return languageRepository.findAll();
    }

    @Override
    public long countComponents() {
        return languageRepository.count();
    }
}
