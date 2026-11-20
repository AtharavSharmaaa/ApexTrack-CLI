package com.apextrack.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initializeDatabase() throws SQLException {
        String createTeamsTable = """
                CREATE TABLE IF NOT EXISTS teams (
                    team_id INT PRIMARY KEY,
                    team_name VARCHAR(100) NOT NULL,
                    engine_supplier VARCHAR(80) NOT NULL,
                    points DOUBLE DEFAULT 0.0
                )
                """;

        String createDriversTable = """
                CREATE TABLE IF NOT EXISTS drivers (
                    driver_id INT PRIMARY KEY,
                    driver_name VARCHAR(100) NOT NULL,
                    nationality VARCHAR(60) NOT NULL,
                    car_number INT NOT NULL,
                    team_id INT,
                    points DOUBLE DEFAULT 0.0,
                    wins INT DEFAULT 0,
                    FOREIGN KEY (team_id) REFERENCES teams(team_id)
                )
                """;

        String createRaceResultsTable = """
                CREATE TABLE IF NOT EXISTS race_results (
                    result_id INT AUTO_INCREMENT PRIMARY KEY,
                    race_name VARCHAR(100) NOT NULL,
                    driver_id INT NOT NULL,
                    position INT NOT NULL,
                    points_earned DOUBLE NOT NULL,
                    FOREIGN KEY (driver_id) REFERENCES drivers(driver_id)
                )
                """;

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTeamsTable);
            stmt.execute(createDriversTable);
            stmt.execute(createRaceResultsTable);
            System.out.println("Database tables initialized successfully.");
        }
    }
}
