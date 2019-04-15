package com.oultoncollege.ComponentCatalog.controllers;

import com.oultoncollege.ComponentCatalog.models.Component;
import com.oultoncollege.ComponentCatalog.repositories.ComponentRepository;
import com.oultoncollege.ComponentCatalog.repositories.LanguageRepository;
import com.oultoncollege.ComponentCatalog.services.ComponentService;
import com.oultoncollege.ComponentCatalog.services.ComponentServiceImpl;
import com.oultoncollege.ComponentCatalog.services.LanguageService;
import com.oultoncollege.ComponentCatalog.services.LanguageServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/component/*")
public class ComponentController {

    private ComponentService compService;
    private LanguageService langService;

    public ComponentController(ComponentRepository compRepository, LanguageRepository langRepository) {
        this.compService = new ComponentServiceImpl(compRepository);
        this.langService = new LanguageServiceImpl(langRepository, compRepository);
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

    @GetMapping("/edit/{id}")
    public String editComponentForm(@PathVariable("id") int id, Model model) {
        Component component = compService.getComponent(id);

        if (component != null)
            model.addAttribute("component", component);

        return "/component/edit";
    }

    @PostMapping("/edit/{id}")
    public String editComponentSubmit(@PathVariable("id") int id, @Valid Component component, BindingResult result, Model model) {
        component.setId(id);
        compService.editComponent(component);
        return "redirect:/component/";
    }

    @GetMapping("/delete/{id}")
    public String deleteComponentForm(@PathVariable("id") int id, Model model) {
        Component component = compService.getComponent(id);

        if (component != null)
            model.addAttribute("language", component);

        return "/component/delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteComponentSubmit(@PathVariable("id") int id, BindingResult result, Model model) {
        Component component = compService.getComponent(id);
        compService.deleteComponent(component);
        return "redirect:/component/";
    }
}
