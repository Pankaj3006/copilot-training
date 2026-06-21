package com.epam.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public final class DatabaseUtil {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/personal";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root@123";

    private DatabaseUtil() {
    }

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException exception) {
            throw new IllegalStateException("MySQL JDBC driver not found.", exception);
        }
    }

    public static List<String> fetchLocationNames(int limit) {
        List<String> locations = new ArrayList<>();
        String query = "SELECT name FROM location LIMIT " + limit;

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                locations.add(resultSet.getString("name"));
            }
        } catch (SQLException exception) {
            throw new IllegalStateException("Failed to fetch locations from database.", exception);
        }

        return locations;
    }
}
