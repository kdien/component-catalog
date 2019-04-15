package com.oultoncollege.ComponentCatalog.controllers;

import com.oultoncollege.ComponentCatalog.models.Language;
import com.oultoncollege.ComponentCatalog.repositories.LanguageRepository;
import com.oultoncollege.ComponentCatalog.services.LanguageService;
import com.oultoncollege.ComponentCatalog.services.LanguageServiceImpl;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/language/*")
public class LanguageController {

    private LanguageService languageService;

    public LanguageController(LanguageRepository repository) {
        languageService = new LanguageServiceImpl(repository);
    }

    @GetMapping
    public String listAllLanguages(Model model) {
        model.addAttribute("languages", languageService.getAllLanguages());
        return "language/index";
    }

    @GetMapping("/create")
    public String createLanguageForm(Model model, @RequestParam(name = "success", required = false) @Nullable Boolean success) {
        model.addAttribute("language", new Language());

        if (success != null)
            model.addAttribute("success", success);

        return "language/create";
    }

    @PostMapping("/create")
    public String createLanguageSubmit(@Valid Language language, BindingResult result, Model model) {
        languageService.createLanguage(language);

        if (result.hasErrors()) {
            model.addAttribute("success", false);
        } else {
            model.addAttribute("success", true);
        }

        return "redirect:/language/create";
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
        languageService.editLanguage(language);
        model.addAttribute("languages", languageService.getAllLanguages());
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
    public String deleteLanguageSubmit(@PathVariable("id") int id, @Valid Language language, BindingResult result, Model model) {
        language.setLangId(id);
        languageService.deleteLanguage(language);
        model.addAttribute("languages", languageService.getAllLanguages());
        return "redirect:/language/";
    }
}
