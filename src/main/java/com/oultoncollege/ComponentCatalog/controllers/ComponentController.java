package com.oultoncollege.ComponentCatalog.controllers;

import com.oultoncollege.ComponentCatalog.models.Component;
import com.oultoncollege.ComponentCatalog.models.Language;
import com.oultoncollege.ComponentCatalog.repositories.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequestMapping(path = "/components")
public class ComponentController {

    @Autowired
    private ComponentRepository componentRepository;

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

        componentRepository.save(c);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Component> getAllComponents() {
        return componentRepository.findAll();
    }

    @GetMapping(path = "/find")
    public @ResponseBody
    Optional<Component> getComponent(@RequestParam int id) {
        return componentRepository.findById(id);
    }
}
