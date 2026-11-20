package com.apextrack.model;

public class ConstructorTeam {
    private int teamId;
    private String teamName;
    private String engineSupplier;
    private double points;

    public ConstructorTeam(int teamId, String teamName, String engineSupplier, double points) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.engineSupplier = engineSupplier;
        this.points = points;
    }

    public int getTeamId() { return teamId; }
    public void setTeamId(int teamId) { this.teamId = teamId; }

    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }

    public String getEngineSupplier() { return engineSupplier; }
    public void setEngineSupplier(String engineSupplier) { this.engineSupplier = engineSupplier; }

    public double getPoints() { return points; }
    public void setPoints(double points) { this.points = points; }

    @Override
    public String toString() {
        return String.format("[%d] %s (%s) - %.1f pts", teamId, teamName, engineSupplier, points);
    }
}
