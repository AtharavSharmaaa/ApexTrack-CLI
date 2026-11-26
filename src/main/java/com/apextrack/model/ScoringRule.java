package com.apextrack.model;

public class ScoringRule {
    private static final double[] F1_POINTS = {25.0, 18.0, 15.0, 12.0, 10.0, 8.0, 6.0, 4.0, 2.0, 1.0};

    public static double getPointsForPosition(int position) {
        if (position >= 1 && position <= F1_POINTS.length) {
            return F1_POINTS[position - 1];
        }
        return 0.0;
    }

    public static double getPointsForPosition(int position, boolean fastestLap) {
        double basePoints = getPointsForPosition(position);
        if (fastestLap && position >= 1 && position <= 10) {
            return basePoints + 1.0;
        }
        return basePoints;
    }
}
