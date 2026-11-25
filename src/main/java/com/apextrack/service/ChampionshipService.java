package com.apextrack.service;

import com.apextrack.dao.DriverDao;
import com.apextrack.dao.RaceResultDao;
import com.apextrack.dao.TeamDao;
import com.apextrack.exception.ChampionshipException;
import com.apextrack.model.ConstructorTeam;
import com.apextrack.model.Driver;
import com.apextrack.model.ScoringRule;

import java.sql.SQLException;
import java.util.List;

public class ChampionshipService {
    private final TeamDao teamDao = new TeamDao();
    private final DriverDao driverDao = new DriverDao();
    private final RaceResultDao raceResultDao = new RaceResultDao();

    public void registerTeam(ConstructorTeam team) throws ChampionshipException {
        try {
            teamDao.addTeam(team);
        } catch (SQLException e) {
            throw new ChampionshipException("Failed to register team: " + team.getTeamName(), e);
        }
    }

    public void registerDriver(Driver driver) throws ChampionshipException {
        try {
            driverDao.addDriver(driver);
        } catch (SQLException e) {
            throw new ChampionshipException("Failed to register driver: " + driver.getName(), e);
        }
    }

    public void recordRaceFinish(String raceName, int driverId, int teamId, int position, boolean fastestLap) throws ChampionshipException {
        double pointsEarned = ScoringRule.getPointsForPosition(position, fastestLap);
        boolean isWin = (position == 1);

        try {
            raceResultDao.recordRaceResult(raceName, driverId, position, pointsEarned);
            driverDao.updateDriverStats(driverId, pointsEarned, isWin);

            List<ConstructorTeam> teams = teamDao.getAllTeams();
            for (ConstructorTeam team : teams) {
                if (team.getTeamId() == teamId) {
                    teamDao.updateTeamPoints(teamId, team.getPoints() + pointsEarned);
                    break;
                }
            }
        } catch (SQLException e) {
            throw new ChampionshipException("Failed to record race result for driver ID " + driverId, e);
        }
    }

    public List<Driver> fetchDriverStandings() throws ChampionshipException {
        try {
            return driverDao.getAllDrivers();
        } catch (SQLException e) {
            throw new ChampionshipException("Failed to fetch driver standings", e);
        }
    }

    public List<ConstructorTeam> fetchConstructorStandings() throws ChampionshipException {
        try {
            return teamDao.getAllTeams();
        } catch (SQLException e) {
            throw new ChampionshipException("Failed to fetch constructor standings", e);
        }
    }
}
