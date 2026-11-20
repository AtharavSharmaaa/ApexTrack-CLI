package com.apextrack.dao;

import com.apextrack.config.DatabaseConfig;
import com.apextrack.model.ConstructorTeam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamDao {

    public void addTeam(ConstructorTeam team) throws SQLException {
        String sql = "INSERT INTO teams (team_id, team_name, engine_supplier, points) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, team.getTeamId());
            pstmt.setString(2, team.getTeamName());
            pstmt.setString(3, team.getEngineSupplier());
            pstmt.setDouble(4, team.getPoints());
            pstmt.executeUpdate();
        }
    }

    public List<ConstructorTeam> getAllTeams() throws SQLException {
        List<ConstructorTeam> teams = new ArrayList<>();
        String sql = "SELECT team_id, team_name, engine_supplier, points FROM teams ORDER BY points DESC";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                ConstructorTeam team = new ConstructorTeam(
                        rs.getInt("team_id"),
                        rs.getString("team_name"),
                        rs.getString("engine_supplier"),
                        rs.getDouble("points")
                );
                teams.add(team);
            }
        }
        return teams;
    }

    public boolean updateTeamPoints(int teamId, double newPoints) throws SQLException {
        String sql = "UPDATE teams SET points = ? WHERE team_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, newPoints);
            pstmt.setInt(2, teamId);
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean deleteTeam(int teamId) throws SQLException {
        String sql = "DELETE FROM teams WHERE team_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, teamId);
            return pstmt.executeUpdate() > 0;
        }
    }
}
