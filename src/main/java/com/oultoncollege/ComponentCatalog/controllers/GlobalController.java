package com.oultoncollege.ComponentCatalog.controllers;

import com.oultoncollege.ComponentCatalog.models.Language;
import com.oultoncollege.ComponentCatalog.repositories.ComponentRepository;
import com.oultoncollege.ComponentCatalog.repositories.LanguageRepository;
import com.oultoncollege.ComponentCatalog.services.LanguageService;
import com.oultoncollege.ComponentCatalog.services.LanguageServiceImpl;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class GlobalController {

    private LanguageService languageService;

    public GlobalController(LanguageRepository languageRepository, ComponentRepository componentRepository) {
        languageService = new LanguageServiceImpl(languageRepository, componentRepository);
    }

    @ModelAttribute("languages")
    public List<Language> getAllLanguages() {
        return languageService.getAllLanguages();
    }
}
