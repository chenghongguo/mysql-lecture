package com.hongguo.mysql.jdbc.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DbUtils {
    public static Connection getConnection() throws SQLException, IOException {
        Properties properties = new Properties();
        try (InputStream in = DbUtils.class.getClassLoader().getResourceAsStream("mysql.properties")) {
            properties.load(in);
        }
        String drivers = properties.getProperty("jdbc.drivers");
        if (drivers != null) {
            System.setProperty("jdbc.drivers", drivers);
        }
        String url = properties.getProperty("jdbc.url");
        String username = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");
        return DriverManager.getConnection(url, username, password);
    }

    public static Map<String, String> getSqlCommand(String sqlName) throws Exception {
        Properties properties = new Properties();
        try (InputStream in = DbUtils.class.getClassLoader().getResourceAsStream(sqlName)) {
            properties.load(in);
        }
        Map<String, String> sqlCommands = new HashMap<>();
        properties.stringPropertyNames().forEach(key -> {
            sqlCommands.put(key, properties.getProperty(key));
        });
        return sqlCommands;
    }
}
