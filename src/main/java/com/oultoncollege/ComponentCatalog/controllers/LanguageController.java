package com.oultoncollege.ComponentCatalog.controllers;

import com.oultoncollege.ComponentCatalog.models.Language;
import com.oultoncollege.ComponentCatalog.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/languages")
public class LanguageController {

    @Autowired
    private LanguageRepository languageRepository;

    @GetMapping(path = "/add")
    public @ResponseBody
    String addNewLanguage
        (@RequestParam String name) {

        Language l = new Language();
        l.setName(name);
        languageRepository.save(l);
        return "Successful";
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

}
