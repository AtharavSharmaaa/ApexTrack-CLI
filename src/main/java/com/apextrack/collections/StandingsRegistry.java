package com.apextrack.collections;

import com.apextrack.model.Driver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StandingsRegistry {
    private final Map<Integer, Driver> driverMap = new HashMap<>();

    public void registerDriver(Driver driver) {
        driverMap.put(driver.getId(), driver);
    }

    public Driver getDriverById(int id) {
        return driverMap.get(id);
    }

    public List<Driver> getSortedStandings() {
        List<Driver> standings = new ArrayList<>(driverMap.values());
        standings.sort(new DriverPointsComparator());
        return standings;
    }

    public int getTotalDrivers() {
        return driverMap.size();
    }
}
