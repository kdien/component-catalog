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
@RequestMapping(path = "/component/*")
public class ComponentController {

    private ComponentService compService;
    private LanguageService langService;

    public ComponentController(ComponentRepository compRepository, LanguageRepository langRepository) {
        this.compService = new ComponentServiceImpl(compRepository);
        this.langService = new LanguageServiceImpl(langRepository);
    }

    @GetMapping(path = "/add")
    public @ResponseBody
    String addNewComponent
        (@RequestParam String name,
         @RequestParam String description,
         @RequestParam @Nullable String html,
         @RequestParam @Nullable String css,
         @RequestParam @Nullable String js,
         @RequestParam @Nullable String code,
         @RequestParam Integer langId) {

        Component c = new Component();
        c.setName(name);
        c.setDescription(description);
        c.setHtml(html);
        c.setCss(css);
        c.setJs(js);
        c.setCode(code);

        Language lang = new Language();
        lang.setLangId(langId);
        c.setLanguage(lang);

        compService.createComponent(c);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Component> getAllComponents() {
        return compService.getAllComponents();
    }

    @GetMapping(path = "")
    public String component(@RequestParam int id, Model model) {
        Component component = compService.getComponent(id);
        Language language = langService.getLanguage(component.getLanguage().getLangId());

        model.addAttribute("component", component);
        model.addAttribute("language", language);

        return "home/component";
    }

    @GetMapping(path = "/create")
    public String createComponent(Model model) {
        model.addAttribute("component", new Component());
        model.addAttribute("languages", langService.getAllLanguages());

        return "component/create";
    }

    @PostMapping(path = "/create")
    public String submitComponent(@ModelAttribute Component component) {
        compService.createComponent(component);

        return "redirect:/";
    }
}
