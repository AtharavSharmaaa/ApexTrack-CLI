package com.apextrack.cli;

import com.apextrack.collections.PitStopQueueManager;
import com.apextrack.collections.StandingsRegistry;
import com.apextrack.concurrency.RaceSimulationEngine;
import com.apextrack.exception.ChampionshipException;
import com.apextrack.model.Driver;
import com.apextrack.service.ChampionshipService;

import java.util.List;
import java.util.Scanner;

public class SimulationCliView {

    private final ChampionshipService championshipService;
    private final RaceSimulationEngine simulationEngine = new RaceSimulationEngine();
    private final PitStopQueueManager pitStopQueue = new PitStopQueueManager();

    public SimulationCliView(ChampionshipService championshipService) {
        this.championshipService = championshipService;
    }

    public void showMenu(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- RACE SIMULATION & PIT LANE MENU ---");
            System.out.println("1. Run Multi-Threaded Race Simulation");
            System.out.println("2. Queue Driver for Pit Stop");
            System.out.println("3. Process Next Pit Stop");
            System.out.println("4. View Sorted Standings Registry");
            System.out.println("0. Back");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> {
                        List<Driver> drivers = championshipService.fetchDriverStandings();
                        System.out.print("Enter number of laps: ");
                        int laps = Integer.parseInt(scanner.nextLine().trim());
                        List<String> results = simulationEngine.runSimulation(drivers, laps);
                        System.out.println("\n=== SIMULATION CLASSIFICATION ===");
                        results.forEach(System.out::println);
                    }
                    case "2" -> {
                        System.out.print("Enter driver name/number for pit stop: ");
                        String entry = scanner.nextLine().trim();
                        pitStopQueue.requestPitStop(entry);
                        System.out.println("Queued for pit stop. Pending: " + pitStopQueue.queueSize());
                    }
                    case "3" -> {
                        String next = pitStopQueue.processNextPitStop();
                        System.out.println(next != null ? "Pit stop completed for: " + next : "Pit lane queue is empty.");
                    }
                    case "4" -> {
                        StandingsRegistry registry = new StandingsRegistry();
                        for (Driver d : championshipService.fetchDriverStandings()) {
                            registry.registerDriver(d);
                        }
                        List<Driver> sorted = registry.getSortedStandings();
                        System.out.println("\n=== REGISTRY STANDINGS ===");
                        sorted.forEach(d -> System.out.println(d.toString()));
                    }
                    case "0" -> running = false;
                    default -> System.out.println("Invalid option.");
                }
            } catch (ChampionshipException | NumberFormatException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
