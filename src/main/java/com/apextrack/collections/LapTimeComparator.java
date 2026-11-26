package com.apextrack.collections;

import java.util.Comparator;

public class LapTimeComparator implements Comparator<Double> {
    @Override
    public int compare(Double lap1, Double lap2) {
        return Double.compare(lap1, lap2);
    }
}
