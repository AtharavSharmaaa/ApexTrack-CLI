package com.apextrack.model;

public class Circuit {
    private int circuitId;
    private String circuitName;
    private String country;
    private int totalLaps;
    private double lapLengthKm;

    public Circuit(int circuitId, String circuitName, String country, int totalLaps, double lapLengthKm) {
        this.circuitId = circuitId;
        this.circuitName = circuitName;
        this.country = country;
        this.totalLaps = totalLaps;
        this.lapLengthKm = lapLengthKm;
    }

    public int getCircuitId() { return circuitId; }
    public void setCircuitId(int circuitId) { this.circuitId = circuitId; }

    public String getCircuitName() { return circuitName; }
    public void setCircuitName(String circuitName) { this.circuitName = circuitName; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public int getTotalLaps() { return totalLaps; }
    public void setTotalLaps(int totalLaps) { this.totalLaps = totalLaps; }

    public double getLapLengthKm() { return lapLengthKm; }
    public void setLapLengthKm(double lapLengthKm) { this.lapLengthKm = lapLengthKm; }

    @Override
    public String toString() {
        return String.format("[%d] %s (%s) - %d laps (%.2f km)", circuitId, circuitName, country, totalLaps, lapLengthKm);
    }
}
