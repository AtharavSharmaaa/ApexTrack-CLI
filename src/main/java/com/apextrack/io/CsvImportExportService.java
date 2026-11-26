package com.apextrack.io;

import com.apextrack.exception.ChampionshipException;
import com.apextrack.model.Driver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CsvImportExportService {

    public void exportDriversToCsv(List<Driver> drivers, String filePath) throws ChampionshipException {
        Path path = Path.of(filePath);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write("id,name,nationality,carNumber,teamId,points,wins");
            writer.newLine();
            for (Driver d : drivers) {
                String line = String.format("%d,%s,%s,%d,%d,%.1f,%d",
                        d.getId(), d.getName(), d.getNationality(),
                        d.getCarNumber(), d.getTeamId(), d.getPoints(), d.getWins());
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new ChampionshipException("Failed to export drivers to CSV: " + filePath, e);
        }
    }

    public List<Driver> importDriversFromCsv(String filePath) throws ChampionshipException {
        List<Driver> imported = new ArrayList<>();
        Path path = Path.of(filePath);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length < 7) {
                    continue;
                }
                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                String nationality = parts[2].trim();
                int carNumber = Integer.parseInt(parts[3].trim());
                int teamId = Integer.parseInt(parts[4].trim());
                double points = Double.parseDouble(parts[5].trim());
                int wins = Integer.parseInt(parts[6].trim());
                imported.add(new Driver(id, name, nationality, carNumber, teamId, points, wins));
            }
        } catch (IOException | NumberFormatException e) {
            throw new ChampionshipException("Failed to import drivers from CSV: " + filePath, e);
        }
        return imported;
    }
}
