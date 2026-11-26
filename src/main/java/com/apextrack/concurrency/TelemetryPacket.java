package com.apextrack.concurrency;

public class TelemetryPacket {
    private final int driverId;
    private final String driverName;
    private final int lapNumber;
    private final double lapTimeSeconds;
    private final double speedKmh;

    public TelemetryPacket(int driverId, String driverName, int lapNumber, double lapTimeSeconds, double speedKmh) {
        this.driverId = driverId;
        this.driverName = driverName;
        this.lapNumber = lapNumber;
        this.lapTimeSeconds = lapTimeSeconds;
        this.speedKmh = speedKmh;
    }

    public int getDriverId() { return driverId; }
    public String getDriverName() { return driverName; }
    public int getLapNumber() { return lapNumber; }
    public double getLapTimeSeconds() { return lapTimeSeconds; }
    public double getSpeedKmh() { return speedKmh; }

    @Override
    public String toString() {
        return String.format("Lap %d | %s (ID %d) | %.3fs | %.1f km/h",
                lapNumber, driverName, driverId, lapTimeSeconds, speedKmh);
    }
}
