package com.apextrack.analytics;

import com.apextrack.model.ConstructorTeam;
import com.apextrack.model.Driver;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ChampionshipAnalyticsService {

    public List<Driver> getTopDrivers(List<Driver> drivers, int limit) {
        return drivers.stream()
                .sorted(Comparator.comparingDouble(Driver::getPoints).reversed()
                        .thenComparingInt(Driver::getWins).reversed())
                .limit(limit)
                .toList();
    }

    public Optional<Driver> getChampionshipLeader(List<Driver> drivers) {
        return drivers.stream()
                .max(Comparator.comparingDouble(Driver::getPoints)
                        .thenComparingInt(Driver::getWins));
    }

    public double calculateAverageDriverPoints(List<Driver> drivers) {
        return drivers.stream()
                .mapToDouble(Driver::getPoints)
                .average()
                .orElse(0.0);
    }

    public Map<String, Double> getPointsByNationality(List<Driver> drivers) {
        return drivers.stream()
                .collect(Collectors.groupingBy(
                        Driver::getNationality,
                        Collectors.summingDouble(Driver::getPoints)
                ));
    }

    public double getTotalConstructorPoints(List<ConstructorTeam> teams) {
        return teams.stream()
                .mapToDouble(ConstructorTeam::getPoints)
                .sum();
    }
}
