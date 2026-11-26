package com.apextrack.collections;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class PitStopQueueManager {
    private final Queue<String> pitQueue = new ArrayDeque<>();

    public void requestPitStop(String driverLabel) {
        pitQueue.offer(driverLabel);
    }

    public String processNextPitStop() {
        return pitQueue.poll();
    }

    public String peekNextPitStop() {
        return pitQueue.peek();
    }

    public boolean isEmpty() {
        return pitQueue.isEmpty();
    }

    public int queueSize() {
        return pitQueue.size();
    }

    public List<String> getPendingStops() {
        return new ArrayList<>(pitQueue);
    }
}
