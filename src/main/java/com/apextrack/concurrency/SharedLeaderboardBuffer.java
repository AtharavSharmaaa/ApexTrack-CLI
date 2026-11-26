package com.apextrack.concurrency;

import java.util.LinkedList;
import java.util.Queue;

public class SharedLeaderboardBuffer {
    private final Queue<TelemetryPacket> buffer = new LinkedList<>();
    private final int capacity;
    private boolean finished = false;

    public SharedLeaderboardBuffer(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void publish(TelemetryPacket packet) throws InterruptedException {
        while (buffer.size() >= capacity) {
            wait();
        }
        buffer.add(packet);
        notifyAll();
    }

    public synchronized TelemetryPacket consume() throws InterruptedException {
        while (buffer.isEmpty() && !finished) {
            wait();
        }
        if (buffer.isEmpty()) {
            return null;
        }
        TelemetryPacket packet = buffer.poll();
        notifyAll();
        return packet;
    }

    public synchronized void markFinished() {
        finished = true;
        notifyAll();
    }
}
