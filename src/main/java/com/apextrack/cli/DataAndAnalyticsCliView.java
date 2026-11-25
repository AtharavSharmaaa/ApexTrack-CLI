package com.apextrack.cli;

import com.apextrack.analytics.ChampionshipAnalyticsService;
import com.apextrack.analytics.ReportFormatter;
import com.apextrack.exception.ChampionshipException;
import com.apextrack.io.BackupLogWriter;
import com.apextrack.io.CsvImportExportService;
import com.apextrack.model.ConstructorTeam;
import com.apextrack.model.Driver;
import com.apextrack.service.ChampionshipService;

import java.util.List;
import java.util.Scanner;

public class DataAndAnalyticsCliView {

    private final ChampionshipService championshipService;
    private final ChampionshipAnalyticsService analyticsService;
    private final ReportFormatter reportFormatter;
    private final CsvImportExportService csvService;
    private final BackupLogWriter logWriter;

    public DataAndAnalyticsCliView(ChampionshipService championshipService) {
        this.championshipService = championshipService;
        this.analyticsService = new ChampionshipAnalyticsService();
        this.reportFormatter = new ReportFormatter();
        this.csvService = new CsvImportExportService();
        this.logWriter = new BackupLogWriter("logs/apextrack-backup.log");
    }

    public void showMenu(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- DATA, ANALYTICS & CSV MENU ---");
            System.out.println("1. View Driver Standings");
            System.out.println("2. View Constructor Standings");
            System.out.println("3. View Analytics Summary");
            System.out.println("4. Export Drivers to CSV");
            System.out.println("5. Import Drivers from CSV");
            System.out.println("6. View Activity Log");
            System.out.println("0. Back");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> {
                        List<Driver> drivers = championshipService.fetchDriverStandings();
                        System.out.print(reportFormatter.formatDriverStandings(drivers));
                    }
                    case "2" -> {
                        List<ConstructorTeam> teams = championshipService.fetchConstructorStandings();
                        System.out.print(reportFormatter.formatConstructorStandings(teams));
                    }
                    case "3" -> {
                        List<Driver> drivers = championshipService.fetchDriverStandings();
                        List<ConstructorTeam> teams = championshipService.fetchConstructorStandings();
                        System.out.printf("Average Driver Points: %.2f%n", analyticsService.calculateAverageDriverPoints(drivers));
                        System.out.printf("Total Constructor Points: %.2f%n", analyticsService.getTotalConstructorPoints(teams));
                        System.out.print(reportFormatter.formatPointsByNationality(analyticsService.getPointsByNationality(drivers)));
                    }
                    case "4" -> {
                        System.out.print("Enter export CSV path (e.g., drivers_export.csv): ");
                        String path = scanner.nextLine().trim();
                        List<Driver> drivers = championshipService.fetchDriverStandings();
                        csvService.exportDriversToCsv(drivers, path);
                        logWriter.writeLog("Exported " + drivers.size() + " drivers to " + path);
                        System.out.println("Exported successfully.");
                    }
                    case "5" -> {
                        System.out.print("Enter import CSV path (e.g., drivers_export.csv): ");
                        String path = scanner.nextLine().trim();
                        List<Driver> drivers = csvService.importDriversFromCsv(path);
                        for (Driver d : drivers) {
                            championshipService.registerDriver(d);
                        }
                        logWriter.writeLog("Imported " + drivers.size() + " drivers from " + path);
                        System.out.println("Imported " + drivers.size() + " drivers.");
                    }
                    case "6" -> System.out.println(logWriter.readAllLogs());
                    case "0" -> running = false;
                    default -> System.out.println("Invalid choice.");
                }
            } catch (ChampionshipException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
