package com.apextrack.exception;

public class ChampionshipException extends Exception {
    public ChampionshipException(String message) {
        super(message);
    }

    public ChampionshipException(String message, Throwable cause) {
        super(message, cause);
    }
}
