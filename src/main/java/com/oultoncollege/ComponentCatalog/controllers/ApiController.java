package com.oultoncollege.ComponentCatalog.controllers;

import com.oultoncollege.ComponentCatalog.models.Component;
import com.oultoncollege.ComponentCatalog.utils.JdbcQuery;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {

    @GetMapping(value = "/api/component", produces = "application/json")
    public String getAllComponentsJson(@RequestParam(required = false) @Nullable String term) {
        StringBuilder sb = new StringBuilder();
        sb.append("{ \"components\":[");

        if (term == null)
            term = "";

        List<Component> components = JdbcQuery.getComponentsWithSearch(term);

        if (components.size() > 0) {
            for (Component component : components) {
                sb.append("{");
                sb.append("\"id\":\"" + component.getId() + "\",");
                sb.append("\"name\":\"" + component.getName() + "\",");
                sb.append("\"language\":\"" + component.getLanguage().getName() + "\"");
                sb.append("},");
            }
            sb.deleteCharAt(sb.length() - 1);
        }

        sb.append("]}");

        return sb.toString();
    }
}
