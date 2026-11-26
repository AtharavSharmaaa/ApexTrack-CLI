package com.apextrack.io;

import com.apextrack.exception.ChampionshipException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

public class BackupLogWriter {

    private final Path logPath;

    public BackupLogWriter(String filePath) {
        this.logPath = Path.of(filePath);
    }

    public void writeLog(String message) throws ChampionshipException {
        try {
            if (logPath.getParent() != null) {
                Files.createDirectories(logPath.getParent());
            }
            String entry = String.format("[%s] %s%n", LocalDateTime.now(), message);
            Files.writeString(logPath, entry, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new ChampionshipException("Failed to write backup log entry", e);
        }
    }

    public String readAllLogs() throws ChampionshipException {
        if (!Files.exists(logPath)) {
            return "No log entries found.";
        }
        try (BufferedWriter ignored = null) {
            return Files.readString(logPath);
        } catch (IOException e) {
            throw new ChampionshipException("Failed to read backup logs", e);
        }
    }
}
