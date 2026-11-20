# ApexTrack — Master Class Registry (33 Classes | 11 Per Member)

## Member 3 (Team Lead): JDBC, Streams & CSV I/O (11 Classes)
1. `com.apextrack.config.DatabaseConfig`
2. `com.apextrack.config.DatabaseInitializer`
3. `com.apextrack.dao.DriverDao`
4. `com.apextrack.dao.TeamDao`
5. `com.apextrack.dao.RaceResultDao`
6. `com.apextrack.service.ChampionshipService`
7. `com.apextrack.analytics.ChampionshipAnalyticsService`
8. `com.apextrack.analytics.ReportFormatter`
9. `com.apextrack.io.CsvImportExportService`
10. `com.apextrack.io.BackupLogWriter`
11. `com.apextrack.cli.DataAndAnalyticsCliView`

## Member 1: Core OOP, Exceptions & Main CLI (11 Classes)
12. `com.apextrack.MainApp`
13. `com.apextrack.model.Participant`
14. `com.apextrack.model.Driver`
15. `com.apextrack.model.TeamPrincipal`
16. `com.apextrack.model.ConstructorTeam`
17. `com.apextrack.model.Circuit`
18. `com.apextrack.model.RaceSession`
19. `com.apextrack.model.ScoringRule`
20. `com.apextrack.exception.ChampionshipException`
21. `com.apextrack.exception.InvalidTelemetryException`
22. `com.apextrack.cli.ConsoleMenuManager`

## Member 2: Collections, Comparators & Simulation (11 Classes)
23. `com.apextrack.collections.GenericRepository`
24. `com.apextrack.collections.StandingsRegistry`
25. `com.apextrack.collections.DriverPointsComparator`
26. `com.apextrack.collections.LapTimeComparator`
27. `com.apextrack.collections.PitStopQueueManager`
28. `com.apextrack.concurrency.TelemetryPacket`
29. `com.apextrack.concurrency.SharedLeaderboardBuffer`
30. `com.apextrack.concurrency.CarTelemetryProducer`
31. `com.apextrack.concurrency.RaceControlConsumer`
32. `com.apextrack.concurrency.RaceSimulationEngine`
33. `com.apextrack.cli.SimulationCliView`
