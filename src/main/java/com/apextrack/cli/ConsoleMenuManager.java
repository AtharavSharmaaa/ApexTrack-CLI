package com.apextrack.cli;

import com.apextrack.config.DatabaseInitializer;
import com.apextrack.exception.ChampionshipException;
import com.apextrack.model.ConstructorTeam;
import com.apextrack.model.Driver;
import com.apextrack.service.ChampionshipService;

import java.sql.SQLException;
import java.util.Scanner;

public class ConsoleMenuManager {

    private final ChampionshipService championshipService = new ChampionshipService();
    private final DataAndAnalyticsCliView dataCliView = new DataAndAnalyticsCliView(championshipService);
    private final SimulationCliView simulationCliView = new SimulationCliView(championshipService);

    public void start() {
        try {
            DatabaseInitializer.initializeDatabase();
        } catch (SQLException e) {
            System.out.println("Database init warning: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== APEXTRACK CHAMPIONSHIP CLI ===");
            System.out.println("1. Register Constructor Team");
            System.out.println("2. Register Driver");
            System.out.println("3. Record Race Result");
            System.out.println("4. Data & Anylitics");
            System.out.println("5. Race Simulation & Pit Lane Menu");
            System.out.println("0. Exit");
            System.out.print("Select option: ");

            String choice = scanner.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> {
                        System.out.print("Team ID: ");
                        int id = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Team Name: ");
                        String name = scanner.nextLine().trim();
                        System.out.print("Engine Supplier: ");
                        String engine = scanner.nextLine().trim();
                        championshipService.registerTeam(new ConstructorTeam(id, name, engine, 0.0));
                        System.out.println("Team registered.");
                    }
                    case "2" -> {
                        System.out.print("Driver ID: ");
                        int id = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Name: ");
                        String name = scanner.nextLine().trim();
                        System.out.print("Nationality: ");
                        String nation = scanner.nextLine().trim();
                        System.out.print("Car Number: ");
                        int carNo = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Team ID: ");
                        int teamId = Integer.parseInt(scanner.nextLine().trim());
                        championshipService.registerDriver(new Driver(id, name, nation, carNo, teamId, 0.0, 0));
                        System.out.println("Driver registered.");
                    }
                    case "3" -> {
                        System.out.print("Race Name: ");
                        String race = scanner.nextLine().trim();
                        System.out.print("Driver ID: ");
                        int driverId = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Team ID: ");
                        int teamId = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Finish Position (1-20): ");
                        int pos = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Fastest Lap? (true/false): ");
                        boolean fl = Boolean.parseBoolean(scanner.nextLine().trim());
                        championshipService.recordRaceFinish(race, driverId, teamId, pos, fl);
                        System.out.println("Race result recorded.");
                    }
                    case "4" -> dataCliView.showMenu(scanner);
                    case "5" -> simulationCliView.showMenu(scanner);
                    case "0" -> running = false;
                    default -> System.out.println("Invalid option.");
                }
            } catch (ChampionshipException | NumberFormatException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
        System.out.println("Exiting ApexTrack CLI.");
    }
}
