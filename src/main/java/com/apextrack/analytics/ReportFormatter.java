package com.apextrack.analytics;

import com.apextrack.model.ConstructorTeam;
import com.apextrack.model.Driver;

import java.util.List;
import java.util.Map;

public class ReportFormatter {

    public String formatDriverStandings(List<Driver> drivers) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== DRIVER CHAMPIONSHIP STANDINGS ===\n");
        int rank = 1;
        for (Driver d : drivers) {
            sb.append(String.format("%d. %s (#%d) [%s] - %.1f pts | %d wins%n",
                    rank++, d.getName(), d.getCarNumber(), d.getNationality(), d.getPoints(), d.getWins()));
        }
        return sb.toString();
    }

    public String formatConstructorStandings(List<ConstructorTeam> teams) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== CONSTRUCTOR STANDINGS ===\n");
        int rank = 1;
        for (ConstructorTeam t : teams) {
            sb.append(String.format("%d. %s (%s) - %.1f pts%n",
                    rank++, t.getTeamName(), t.getEngineSupplier(), t.getPoints()));
        }
        return sb.toString();
    }

    public String formatPointsByNationality(Map<String, Double> nationalityPoints) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== POINTS BY NATIONALITY ===\n");
        nationalityPoints.forEach((nation, pts) ->
                sb.append(String.format("- %s: %.1f pts%n", nation, pts)));
        return sb.toString();
    }
}
