package com.contentFormula.content.f1racereport.application.usecases;

import com.contentFormula.content.f1racereport.domain.model.F1RaceReport;
import com.contentFormula.content.f1racereport.domain.port.in.F1RaceReportUseCase;
import com.contentFormula.content.f1racereport.domain.port.out.F1RaceReportRepositoryPort;
import lombok.AllArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class F1RaceReportUseCaseImpl implements F1RaceReportUseCase {
    private final F1RaceReportRepositoryPort f1RaceRepositoryPort;

    @Override
    public Optional<F1RaceReport> createRaceReport(F1RaceReport f1RaceReport, String raceId) {
        return f1RaceRepositoryPort.saveRaceReport(f1RaceReport,raceId);
    }

    @Override
    public Optional<F1RaceReport> getRaceReport(Long id) {
        return f1RaceRepositoryPort.getRaceReport(id);
    }

    @Override
    public List<F1RaceReport> getALlRaceReports() {
        return f1RaceRepositoryPort.getAllRaceReports();
    }

    @Override
    public Optional<F1RaceReport> getRaceReportByDate(ZonedDateTime date) {
        return f1RaceRepositoryPort.getRaceReportByDate(date);
    }

    @Override
    public Optional<F1RaceReport> getRaceReportByRaceId(String eventId) {
        return f1RaceRepositoryPort.getRaceReportByRaceId(eventId);
    }

    @Override
    public Optional<List<Long>> getDrivers() {
        return f1RaceRepositoryPort.getDrivers();
    }
}
