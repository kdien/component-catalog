package com.oultoncollege.ComponentCatalog.controllers;

import com.oultoncollege.ComponentCatalog.models.Component;
import com.oultoncollege.ComponentCatalog.models.Language;
import com.oultoncollege.ComponentCatalog.repositories.ComponentRepository;
import com.oultoncollege.ComponentCatalog.repositories.LanguageRepository;
import com.oultoncollege.ComponentCatalog.services.ComponentService;
import com.oultoncollege.ComponentCatalog.services.ComponentServiceImpl;
import com.oultoncollege.ComponentCatalog.services.LanguageService;
import com.oultoncollege.ComponentCatalog.services.LanguageServiceImpl;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/component/*")
public class ComponentController {

    private ComponentService compService;
    private LanguageService langService;

    public ComponentController(ComponentRepository compRepository, LanguageRepository langRepository) {
        this.compService = new ComponentServiceImpl(compRepository);
        this.langService = new LanguageServiceImpl(langRepository);
    }

    @GetMapping
    public String listAllComponents(Model model) {
        model.addAttribute("components", compService.getAllComponents());
        return "component/index";
    }

    @GetMapping("/create")
    public String createComponentForm(Model model) {
        model.addAttribute("component", new Component());
        model.addAttribute("languages", langService.getAllLanguages());
        return "component/create";
    }

    @PostMapping("/create")
    public String createComponentSubmit(@ModelAttribute Component component) {
        compService.createComponent(component);
        return "redirect:/component/";
    }
}
