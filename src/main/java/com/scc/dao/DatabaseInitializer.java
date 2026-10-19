package com.scc.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void initialize() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            String createTableSQL = "CREATE TABLE IF NOT EXISTS appointments (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "patient_name TEXT NOT NULL, " +
                    "doctor_name TEXT NOT NULL, " +
                    "consult_fee REAL NOT NULL, " +
                    "status TEXT NOT NULL);";

            stmt.execute(createTableSQL);

            // Automatic sample data para naay sulod dayon inig view nimo
            String insertSample1 = "INSERT INTO appointments (patient_name, doctor_name, consult_fee, status) " +
                    "SELECT 'Juan Dela Cruz', 'Dr. Jose Rizal', 800.00, 'SCHEDULED' " +
                    "WHERE NOT EXISTS (SELECT 1 FROM appointments WHERE patient_name = 'Juan Dela Cruz');";

            String insertSample2 = "INSERT INTO appointments (patient_name, doctor_name, consult_fee, status) " +
                    "SELECT 'Maria Santos', 'Dr. Antonio Luna', 1000.00, 'COMPLETED' " +
                    "WHERE NOT EXISTS (SELECT 1 FROM appointments WHERE patient_name = 'Maria Santos');";

            String insertSample3 = "INSERT INTO appointments (patient_name, doctor_name, consult_fee, status) " +
                    "SELECT 'Pedro Penduko', 'Dr. Jose Rizal', 800.00, 'SCHEDULED' " +
                    "WHERE NOT EXISTS (SELECT 1 FROM appointments WHERE patient_name = 'Pedro Penduko');";

            stmt.execute(insertSample1);
            stmt.execute(insertSample2);
            stmt.execute(insertSample3);

            System.out.println("Database initialized successfully with default records.");
        } catch (SQLException e) {
            System.err.println("Database initialization error: " + e.getMessage());
        }
    }
}