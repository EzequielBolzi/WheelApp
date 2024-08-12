package com.contentFormula.content.f1racereport.domain.port.out;

import com.contentFormula.content.f1racereport.domain.model.F1RaceReport;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface F1RaceReportRepositoryPort {
    Optional<F1RaceReport> saveRaceReport(F1RaceReport f1RaceReport, String raceId);
    List<F1RaceReport> getAllRaceReports();
    Optional<F1RaceReport> getRaceReport(Long id);
    Optional<F1RaceReport> getRaceReportByDate(ZonedDateTime date);
    Optional<F1RaceReport> getRaceReportByRaceId(String eventId);
    Optional<List<Long>> getDrivers();

}
