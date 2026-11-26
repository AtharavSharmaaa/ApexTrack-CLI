package com.apextrack.cli;

import com.apextrack.analytics.ChampionshipAnalyticsService;
import com.apextrack.analytics.ReportFormatter;
import com.apextrack.exception.ChampionshipException;
import com.apextrack.model.ConstructorTeam;
import com.apextrack.model.Driver;
import com.apextrack.service.ChampionshipService;
import java.util.List;
import java.util.Scanner;

public class DataAndAnalyticsCliView {

    private final ChampionshipService championshipService;
    private final ChampionshipAnalyticsService analyticsService;
    private final ReportFormatter reportFormatter;

    public DataAndAnalyticsCliView(ChampionshipService championshipService) {
        this.championshipService = championshipService;
        this.analyticsService = new ChampionshipAnalyticsService();
        this.reportFormatter = new ReportFormatter();
    }

    public void showMenu(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- DATA & ANALYTICS MENU ---");
            System.out.println("1. View Driver Standings");
            System.out.println("2. View Constructor Standings");
            System.out.println("3. View Analytics Summary");
            System.out.println("0. Back");
            System.out.print("Select option: ");
            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1" -> {
                        List<Driver> drivers = championshipService.fetchDriverStandings();
                        System.out.println(reportFormatter.formatDriverStandings(drivers));
                    }
                    case "2" -> {
                        List<ConstructorTeam> teams = championshipService.fetchConstructorStandings();
                        System.out.println(reportFormatter.formatConstructorStandings(teams));
                    }
                    case "3" -> {
                        List<Driver> drivers = championshipService.fetchDriverStandings();
                        List<ConstructorTeam> teams = championshipService.fetchConstructorStandings();
                        double avgDriverPts = analyticsService.calculateAverageDriverPoints(drivers);
                        double totalTeamPts = teams.stream().mapToDouble(ConstructorTeam::getPoints).sum();
                        System.out.printf("Average Driver Points: %.2f%n", avgDriverPts);
                        System.out.printf("Total Constructor Points: %.2f%n", totalTeamPts);                    }
                    case "0" -> back = true;
                    default -> System.out.println("Invalid option. Try again.");
                }
            } catch (ChampionshipException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
