package com.oultoncollege.ComponentCatalog.controllers;

import com.oultoncollege.ComponentCatalog.repositories.ComponentRepository;
import com.oultoncollege.ComponentCatalog.repositories.LanguageRepository;
import com.oultoncollege.ComponentCatalog.services.ComponentService;
import com.oultoncollege.ComponentCatalog.services.ComponentServiceImpl;
import com.oultoncollege.ComponentCatalog.services.LanguageService;
import com.oultoncollege.ComponentCatalog.services.LanguageServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/*")
public class HomeController {

    private ComponentService compService;
    private LanguageService langService;

    public HomeController(ComponentRepository compRepository, LanguageRepository langRepository) {
        this.compService = new ComponentServiceImpl(compRepository);
        this.langService = new LanguageServiceImpl(langRepository);
    }

    @RequestMapping
    public String index(Model model) {
        model.addAttribute("languages", langService.getAllLanguages());

        return "index";
    }

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {

        String pageTitle = "Greetings";
        model.addAttribute("name", name);
        model.addAttribute("pageTitle", pageTitle);

        return "home/greeting";
    }
}
