package com.oultoncollege.ComponentCatalog.services;

import com.oultoncollege.ComponentCatalog.models.Language;

import java.util.List;

public interface LanguageService {

    Language createLanguage(Language language);

    Language getLanguage(int id);

    Language editLanguage(Language language);

    void deleteLanguage(Language language);

    void deleteLanguage(int id);

    List<Language> getAllLanguages();

    long countLanguages();
}
