package com.apextrack.dao;

import com.apextrack.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RaceResultDao {

    public void recordRaceResult(String raceName, int driverId, int position, double pointsEarned) throws SQLException {
        String sql = "INSERT INTO race_results (race_name, driver_id, position, points_earned) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, raceName);
            pstmt.setInt(2, driverId);
            pstmt.setInt(3, position);
            pstmt.setDouble(4, pointsEarned);
            pstmt.executeUpdate();
        }
    }

    public List<String> getResultsByRace(String raceName) throws SQLException {
        List<String> results = new ArrayList<>();
        String sql = """
                SELECT r.position, d.driver_name, r.points_earned
                FROM race_results r
                JOIN drivers d ON r.driver_id = d.driver_id
                WHERE r.race_name = ?
                ORDER BY r.position ASC
                """;
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, raceName);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String row = String.format("P%d - %s (%.1f pts)",
                            rs.getInt("position"),
                            rs.getString("driver_name"),
                            rs.getDouble("points_earned"));
                    results.add(row);
                }
            }
        }
        return results;
    }
}
