package com.apextrack.concurrency;

import com.apextrack.model.Driver;
import java.util.List;
import java.util.Random;

public class CarTelemetryProducer implements Runnable {
    private final List<Driver> drivers;
    private final int totalLaps;
    private final SharedLeaderboardBuffer buffer;
    private final Random random = new Random();

    public CarTelemetryProducer(List<Driver> drivers, int totalLaps, SharedLeaderboardBuffer buffer) {
        this.drivers = drivers;
        this.totalLaps = totalLaps;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int lap = 1; lap <= totalLaps; lap++) {
                for (Driver driver : drivers) {
                    double lapTime = 74.0 + random.nextDouble() * 6.5;
                    double topSpeed = 295.0 + random.nextDouble() * 32.0;
                    TelemetryPacket packet = new TelemetryPacket(
                            driver.getId(), driver.getName(), lap, lapTime, topSpeed
                    );
                    buffer.publish(packet);
                }
                Thread.sleep(60);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            buffer.markFinished();
        }
    }
}
