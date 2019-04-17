package com.oultoncollege.ComponentCatalog.controllers;

import com.oultoncollege.ComponentCatalog.config.JdbcConnection;
import com.oultoncollege.ComponentCatalog.models.Component;
import com.oultoncollege.ComponentCatalog.models.Language;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiController {

    @GetMapping(value = "/api/component", produces = "application/json")
    public String getAllComponentsJson(@RequestParam(required = false) @Nullable String searchName) {
        StringBuilder sb = new StringBuilder();
        sb.append("{ \"components\":[");

        if (searchName == null)
            searchName = "";

        List<Component> components = getComponentsWithSearch(searchName);

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

    private List<Component> getComponentsWithSearch(String searchName) {
        List<Component> components = new ArrayList<>();
        Connection connection = JdbcConnection.getJdbcConnection();

        String sql = "SELECT component_id, " +
            "c.name," +
            "description, " +
            "html, " +
            "css, " +
            "js, " +
            "code, " +
            "l.language_id, " +
            "l.name AS language_name " +
            "FROM component c " +
            "JOIN `language` l ON c.language_id = l.language_id " +
            "WHERE c.name LIKE ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, "%" + searchName.trim() + "%");
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                int componentId = resultSet.getInt(1);
                String componentName = resultSet.getString(2);
                String description = resultSet.getString(3);
                String html = resultSet.getString(4);
                String css = resultSet.getString(5);
                String js = resultSet.getString(6);
                String code = resultSet.getString(7);
                int languageId = resultSet.getInt(8);
                String languageName = resultSet.getString(9);

                Language language = new Language(languageId, languageName);
                Component component = new Component(componentId, componentName, description, html, css, js, code, language);

                components.add(component);
            }

            resultSet.close();
            connection.close();
            return components;

        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }
}
