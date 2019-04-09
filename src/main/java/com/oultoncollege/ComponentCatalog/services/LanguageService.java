package com.oultoncollege.ComponentCatalog.services;

import com.oultoncollege.ComponentCatalog.models.Language;
import java.util.List;

public interface LanguageService {

    Language createComponent(Language language);

    Language getComponent(int id);

    Language editComponent(Language language);

    void deleteComponent(Language language);

    void deleteComponent(int id);

    List<Language> getAllComponents();

    long countComponents();
}
