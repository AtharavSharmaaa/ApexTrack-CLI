module apextrack.cli {
    requires transitive java.sql;

    exports com.apextrack;
    exports com.apextrack.config;
    exports com.apextrack.model;
    exports com.apextrack.exception;
    exports com.apextrack.dao;
    exports com.apextrack.service;
    exports com.apextrack.analytics;
    exports com.apextrack.io;
    exports com.apextrack.cli;
}
