package com.apextrack.model;

public class TeamPrincipal extends Participant {
    private int teamId;
    private int yearsExperience;

    public TeamPrincipal(int id, String name, String nationality, int teamId, int yearsExperience) {
        super(id, name, nationality);
        this.teamId = teamId;
        this.yearsExperience = yearsExperience;
    }

    @Override
    public String getRoleDetails() {
        return "Team Principal | Team ID: " + teamId + " | Experience: " + yearsExperience + " yrs";
    }

    public int getTeamId() { return teamId; }
    public void setTeamId(int teamId) { this.teamId = teamId; }

    public int getYearsExperience() { return yearsExperience; }
    public void setYearsExperience(int yearsExperience) { this.yearsExperience = yearsExperience; }

    @Override
    public String toString() {
        return String.format("[%d] %s (%s) - Team %d (%d yrs exp)", id, name, nationality, teamId, yearsExperience);
    }
}
