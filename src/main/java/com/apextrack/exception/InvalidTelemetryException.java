package com.apextrack.exception;

public class InvalidTelemetryException extends ChampionshipException {
    public InvalidTelemetryException(String message) {
        super(message);
    }

    public InvalidTelemetryException(String message, Throwable cause) {
        super(message, cause);
    }
}
