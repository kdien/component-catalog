package com.oultoncollege.ComponentCatalog.utils;

import com.oultoncollege.ComponentCatalog.config.JdbcConnection;
import com.oultoncollege.ComponentCatalog.models.Component;
import com.oultoncollege.ComponentCatalog.models.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcQuery {

    public static List<Component> getComponentsWithSearch(String term) {
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
            stmt.setString(1, "%" + term.trim() + "%");
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
