package com.apextrack.collections;

import com.apextrack.model.Driver;
import java.util.Comparator;

public class DriverPointsComparator implements Comparator<Driver> {
    @Override
    public int compare(Driver d1, Driver d2) {
        int pointsCompare = Double.compare(d2.getPoints(), d1.getPoints());
        if (pointsCompare != 0) {
            return pointsCompare;
        }
        return Integer.compare(d2.getWins(), d1.getWins());
    }
}
