package com.apextrack.dao;

import com.apextrack.config.DatabaseConfig;
import com.apextrack.model.Driver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverDao {

    public void addDriver(Driver driver) throws SQLException {
        String sql = "INSERT INTO drivers (driver_id, driver_name, nationality, car_number, team_id, points, wins) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, driver.getId());
            pstmt.setString(2, driver.getName());
            pstmt.setString(3, driver.getNationality());
            pstmt.setInt(4, driver.getCarNumber());
            pstmt.setInt(5, driver.getTeamId());
            pstmt.setDouble(6, driver.getPoints());
            pstmt.setInt(7, driver.getWins());
            pstmt.executeUpdate();
        }
    }

    public List<Driver> getAllDrivers() throws SQLException {
        List<Driver> drivers = new ArrayList<>();
        String sql = "SELECT driver_id, driver_name, nationality, car_number, team_id, points, wins FROM drivers ORDER BY points DESC, wins DESC";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Driver driver = new Driver(
                        rs.getInt("driver_id"),
                        rs.getString("driver_name"),
                        rs.getString("nationality"),
                        rs.getInt("car_number"),
                        rs.getInt("team_id"),
                        rs.getDouble("points"),
                        rs.getInt("wins")
                );
                drivers.add(driver);
            }
        }
        return drivers;
    }

    public boolean updateDriverStats(int driverId, double pointsToAdd, boolean isWin) throws SQLException {
        String sql = "UPDATE drivers SET points = points + ?, wins = wins + ? WHERE driver_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, pointsToAdd);
            pstmt.setInt(2, isWin ? 1 : 0);
            pstmt.setInt(3, driverId);
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean deleteDriver(int driverId) throws SQLException {
        String sql = "DELETE FROM drivers WHERE driver_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, driverId);
            return pstmt.executeUpdate() > 0;
        }
    }
}
