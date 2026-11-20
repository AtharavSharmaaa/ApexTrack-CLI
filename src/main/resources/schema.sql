CREATE DATABASE IF NOT EXISTS apextrack_db;
USE apextrack_db;

CREATE TABLE IF NOT EXISTS teams (
    team_id INT PRIMARY KEY,
    team_name VARCHAR(100) NOT NULL,
    engine_supplier VARCHAR(80) NOT NULL,
    points DOUBLE DEFAULT 0.0
);

CREATE TABLE IF NOT EXISTS drivers (
    driver_id INT PRIMARY KEY,
    driver_name VARCHAR(100) NOT NULL,
    nationality VARCHAR(60) NOT NULL,
    car_number INT NOT NULL,
    team_id INT,
    points DOUBLE DEFAULT 0.0,
    wins INT DEFAULT 0,
    FOREIGN KEY (team_id) REFERENCES teams(team_id)
);

CREATE TABLE IF NOT EXISTS race_results (
    result_id INT AUTO_INCREMENT PRIMARY KEY,
    race_name VARCHAR(100) NOT NULL,
    driver_id INT NOT NULL,
    position INT NOT NULL,
    points_earned DOUBLE NOT NULL,
    FOREIGN KEY (driver_id) REFERENCES drivers(driver_id)
);
