package com.apextrack.concurrency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RaceControlConsumer implements Runnable {
    private final SharedLeaderboardBuffer buffer;
    private final Map<String, Double> cumulativeTimes = new HashMap<>();
    private final List<String> feedLog = new ArrayList<>();

    public RaceControlConsumer(SharedLeaderboardBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            TelemetryPacket packet;
            while ((packet = buffer.consume()) != null) {
                String name = packet.getDriverName();
                double updatedTotal = cumulativeTimes.getOrDefault(name, 0.0) + packet.getLapTimeSeconds();
                cumulativeTimes.put(name, updatedTotal);
                feedLog.add(packet.toString());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public Map<String, Double> getCumulativeTimes() {
        return new HashMap<>(cumulativeTimes);
    }

    public List<String> getFeedLog() {
        return new ArrayList<>(feedLog);
    }
}
