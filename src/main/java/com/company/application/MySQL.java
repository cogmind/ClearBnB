package com.company.application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    private static Connection con;

    public MySQL() {
        // Singleton
        if (con == null) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ClearBnB", "root", "root");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection connect() {
        return con;
    }
}
