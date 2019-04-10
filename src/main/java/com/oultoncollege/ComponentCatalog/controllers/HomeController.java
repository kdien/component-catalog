package com.oultoncollege.ComponentCatalog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/*")
public class HomeController {

    @RequestMapping
    public String index() {
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
