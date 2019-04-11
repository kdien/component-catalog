package com.oultoncollege.ComponentCatalog.controllers;

import com.oultoncollege.ComponentCatalog.models.Language;
import com.oultoncollege.ComponentCatalog.repositories.LanguageRepository;
import com.oultoncollege.ComponentCatalog.services.LanguageService;
import com.oultoncollege.ComponentCatalog.services.LanguageServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/language/*")
public class LanguageController {

    private LanguageService languageService;

    public LanguageController(LanguageRepository repository) {
        languageService = new LanguageServiceImpl(repository);
    }

    @GetMapping(path = "/create")
    public String createLanguageForm(Model model, @RequestParam(name = "success", required = false) Optional<Boolean> success) {
        model.addAttribute("language", new Language());

        if (success.isPresent())
            model.addAttribute("success", success.get());

        return "language/create";
    }

    @PostMapping(path = "/create")
    public String createLanguageSubmit(@ModelAttribute Language language, BindingResult result, Model model) {
        languageService.createLanguage(language);

        if (result.hasErrors()) {
            model.addAttribute("success", false);
        } else {
            model.addAttribute("success", true);
        }

        return "redirect:/language/create";
    }
}
