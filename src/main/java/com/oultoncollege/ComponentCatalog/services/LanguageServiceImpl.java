package com.oultoncollege.ComponentCatalog.services;

import com.oultoncollege.ComponentCatalog.models.Component;
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
    public Language createLanguage(Language language) {
        return languageRepository.save(language);
    }

    @Override
    public Language getLanguage(int id) {
        if(languageRepository.findById(id).isPresent()) {
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
        languageRepository.delete(language);
    }

    @Override
    public void deleteLanguage(int id) {
        languageRepository.deleteById(id);
    }

    @Override
    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    @Override
    public long countLanguages() {
        return languageRepository.count();
    }

    @Override
    public List<Component> getComponents(Language language) {
        // get all components with the same language ID as the passed language
        Language selectedLang = getLanguage(language.getLangId());
        return null;
    }
}
