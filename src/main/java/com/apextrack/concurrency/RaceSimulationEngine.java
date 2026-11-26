package com.apextrack.concurrency;

import com.apextrack.exception.ChampionshipException;
import com.apextrack.model.Driver;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class RaceSimulationEngine {

    public List<String> runSimulation(List<Driver> drivers, int laps) throws ChampionshipException {
        if (drivers == null || drivers.isEmpty()) {
            throw new ChampionshipException("Cannot run race simulation with no drivers.");
        }
        if (laps < 1) {
            throw new ChampionshipException("Lap count must be at least 1.");
        }

        SharedLeaderboardBuffer buffer = new SharedLeaderboardBuffer(10);
        CarTelemetryProducer producer = new CarTelemetryProducer(drivers, laps, buffer);
        RaceControlConsumer consumer = new RaceControlConsumer(buffer);

        Thread producerThread = new Thread(producer, "Telemetry-Producer");
        Thread consumerThread = new Thread(consumer, "Race-Control-Consumer");

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ChampionshipException("Race simulation was interrupted.", e);
        }

        Map<String, Double> totals = consumer.getCumulativeTimes();
        List> ranked = new ArrayList<>(totals.entrySet());
        ranked.sort(Comparator.comparingDouble(Map.Entry::getValue));

        List<String> classification = new ArrayList<>();
        int pos = 1;
        for (Map.Entry<String, Double> entry : ranked) {
            classification.add(String.format("P%d - %s (Total Time: %.3fs)", pos++, entry.getKey(), entry.getValue()));
        }
        return classification;
    }
}
