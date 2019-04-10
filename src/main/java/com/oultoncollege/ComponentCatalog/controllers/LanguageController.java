package com.oultoncollege.ComponentCatalog.controllers;

import com.oultoncollege.ComponentCatalog.models.Language;
import com.oultoncollege.ComponentCatalog.repositories.LanguageRepository;
import com.oultoncollege.ComponentCatalog.services.LanguageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/language/*")
public class LanguageController {

    @Autowired
    private LanguageRepository languageRepository;

    @GetMapping(path = "/create")
    public String createLanguageForm(Model model) {


        model.addAttribute("language", new Language());
        return "language/create";
    }

    @PostMapping(path = "/create")
    public String createLanguageSubmit(@ModelAttribute Language language, BindingResult result, Model model) {
        LanguageServiceImpl languageService = new LanguageServiceImpl(languageRepository);
        languageService.createLanguage(language);

        if (result.hasErrors()) {
            model.addAttribute("success", false);
        } else {
            model.addAttribute("success", true);
        }

        return "redirect:/language/create";
    }
}
