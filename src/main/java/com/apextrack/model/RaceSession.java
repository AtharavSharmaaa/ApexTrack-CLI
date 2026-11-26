package com.apextrack.model;

public class RaceSession {
    private int sessionId;
    private String raceName;
    private int circuitId;
    private String sessionType;
    private boolean completed;

    public RaceSession(int sessionId, String raceName, int circuitId, String sessionType, boolean completed) {
        this.sessionId = sessionId;
        this.raceName = raceName;
        this.circuitId = circuitId;
        this.sessionType = sessionType;
        this.completed = completed;
    }

    public int getSessionId() { return sessionId; }
    public void setSessionId(int sessionId) { this.sessionId = sessionId; }

    public String getRaceName() { return raceName; }
    public void setRaceName(String raceName) { this.raceName = raceName; }

    public int getCircuitId() { return circuitId; }
    public void setCircuitId(int circuitId) { this.circuitId = circuitId; }

    public String getSessionType() { return sessionType; }
    public void setSessionType(String sessionType) { this.sessionType = sessionType; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    @Override
    public String toString() {
        String status = completed ? "Completed" : "Scheduled";
        return String.format("[%d] %s (%s) - %s", sessionId, raceName, sessionType, status);
    }
}
