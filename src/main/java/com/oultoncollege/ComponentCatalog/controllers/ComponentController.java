package com.oultoncollege.ComponentCatalog.controllers;

import com.oultoncollege.ComponentCatalog.models.Component;
import com.oultoncollege.ComponentCatalog.repositories.ComponentRepository;
import com.oultoncollege.ComponentCatalog.repositories.LanguageRepository;
import com.oultoncollege.ComponentCatalog.services.ComponentService;
import com.oultoncollege.ComponentCatalog.services.ComponentServiceImpl;
import com.oultoncollege.ComponentCatalog.services.LanguageService;
import com.oultoncollege.ComponentCatalog.services.LanguageServiceImpl;
import com.oultoncollege.ComponentCatalog.utils.JdbcQuery;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public String listAllComponents(Model model, @RequestParam(required = false) @Nullable String name) {
        List<Component> components = (name == null) ? compService.getAllComponents() : JdbcQuery.getComponentsWithSearch(name);

        if (components.size() > 0) {
            for (Component component : components) {
                if (component.getName().length() > 25) {
                    component.setName(component.getName().substring(0, 25) + "...");
                }
                if (component.getDescription().length() > 40) {
                    component.setDescription(component.getDescription().substring(0, 40) + "...");
                }
            }
        }

        model.addAttribute("components", components);
        return "component/index";
    }

    @GetMapping("/{id}")
    public String getComponentById(@PathVariable("id") int id, Model model) {
        Component component = compService.getComponent(id);

        if (component != null)
            model.addAttribute("component", component);

        return "/component/details";
    }

    @GetMapping("/create")
    public String createComponentForm(Model model) {
        model.addAttribute("component", new Component());
        model.addAttribute("languages", langService.getAllLanguages());
        return "component/create";
    }

    @PostMapping("/create")
    public String createComponentSubmit(@ModelAttribute Component component) {
        component.setName(component.getName().trim());
        component.setDescription(component.getDescription().trim());
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
        component.setName(component.getName().trim());
        component.setDescription(component.getDescription().trim());
        compService.editComponent(component);
        return "redirect:/component/";
    }

    @GetMapping("/delete/{id}")
    public String deleteComponentForm(@PathVariable("id") int id, Model model) {
        Component component = compService.getComponent(id);

        if (component != null)
            model.addAttribute("component", component);

        return "/component/delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteComponentSubmit(@PathVariable("id") int id, Model model) {
//        Component component = compService.getComponent(id);
        compService.deleteComponent(id);
        return "redirect:/component/";
    }
}
