package com.oultoncollege.ComponentCatalog.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcConnection {

    private static Properties props = new Properties();

    public static Connection getJdbcConnection() {
        Connection connection = null;

        InputStream inputStream = JdbcConnection.class.getResourceAsStream("/application.properties");

        try {
            props.load(inputStream);
        } catch (IOException e) {
            System.err.println(e);
        }

        String dbUrl = props.getProperty("spring.datasource.url");
        String username = props.getProperty("spring.datasource.username");
        String password = props.getProperty("spring.datasource.password");

        try {
            connection = DriverManager.getConnection(dbUrl, username, password);
        } catch (SQLException e) {
            System.err.println(e);
        }

        return connection;
    }
}
