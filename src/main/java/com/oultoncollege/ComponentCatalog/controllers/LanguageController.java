package com.oultoncollege.ComponentCatalog.controllers;

import com.oultoncollege.ComponentCatalog.models.Language;
import com.oultoncollege.ComponentCatalog.repositories.ComponentRepository;
import com.oultoncollege.ComponentCatalog.repositories.LanguageRepository;
import com.oultoncollege.ComponentCatalog.services.LanguageService;
import com.oultoncollege.ComponentCatalog.services.LanguageServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/language/*")
public class LanguageController {

    private LanguageService languageService;

    public LanguageController(LanguageRepository languageRepository, ComponentRepository componentRepository) {
        languageService = new LanguageServiceImpl(languageRepository, componentRepository);
    }

    @GetMapping
    public String listAllLanguages(Model model) {
        model.addAttribute("language", new Language());
        return "language/index";
    }

    @GetMapping("/create")
    public String createLanguageForm(Model model) {
        model.addAttribute("language", new Language());
        return "language/create";
    }

    @PostMapping("/create")
    public String createLanguageSubmit(@Valid Language language, BindingResult result, Model model) {
        language.setName(language.getName().trim());
        languageService.createLanguage(language);
        return "redirect:/language/";
    }

    @GetMapping("/edit/{id}")
    public String editLanguageForm(@PathVariable("id") int id, Model model) {
        Language language = languageService.getLanguage(id);

        if (language != null)
            model.addAttribute("language", language);

        return "/language/edit";
    }

    @PostMapping("/edit/{id}")
    public String editLanguageSubmit(@PathVariable("id") int id, @Valid Language language, BindingResult result, Model model) {
        language.setLangId(id);
        language.setName(language.getName().trim());
        language.setComponents(languageService.getLanguage(id).getComponents());
        languageService.editLanguage(language);
        return "redirect:/language/";
    }

    @GetMapping("/delete/{id}")
    public String deleteLanguageForm(@PathVariable("id") int id, Model model) {
        Language language = languageService.getLanguage(id);

        if (language != null)
            model.addAttribute("language", language);

        return "/language/delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteLanguageSubmit(@PathVariable("id") int id, Model model) {
        Language language = languageService.getLanguage(id);
        languageService.deleteLanguage(language);
        return "redirect:/language/";
    }
}
