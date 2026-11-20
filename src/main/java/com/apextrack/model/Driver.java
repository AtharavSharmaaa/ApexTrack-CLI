package com.apextrack.model;

public class Driver extends Participant {
    private int carNumber;
    private int teamId;
    private double points;
    private int wins;

    public Driver(int id, String name, String nationality, int carNumber, int teamId, double points, int wins) {
        super(id, name, nationality);
        this.carNumber = carNumber;
        this.teamId = teamId;
        this.points = points;
        this.wins = wins;
    }

    @Override
    public String getRoleDetails() {
        return "Driver #" + carNumber + " | Points: " + points + " | Wins: " + wins;
    }

    public int getCarNumber() { return carNumber; }
    public void setCarNumber(int carNumber) { this.carNumber = carNumber; }

    public int getTeamId() { return teamId; }
    public void setTeamId(int teamId) { this.teamId = teamId; }

    public double getPoints() { return points; }
    public void setPoints(double points) { this.points = points; }

    public int getWins() { return wins; }
    public void setWins(int wins) { this.wins = wins; }

    @Override
    public String toString() {
        return String.format("[%d] %s (#%d) - %.1f pts (%d wins)", id, name, carNumber, points, wins);
    }
}
