package com.contentFormula.content.f1racereport.application.service;

import com.contentFormula.content.f1racereport.domain.model.F1RaceReport;
import com.contentFormula.content.f1racereport.domain.port.in.F1RaceReportUseCase;
import lombok.AllArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class F1RaceReportService implements F1RaceReportUseCase {

    private final F1RaceReportUseCase f1RaceReportUseCase;

    @Override
    public Optional<F1RaceReport> createRaceReport(F1RaceReport f1RaceReport, String raceId) {
        return f1RaceReportUseCase.createRaceReport(f1RaceReport,raceId);
    }

    @Override
    public Optional<F1RaceReport> getRaceReport(Long id) {
        return f1RaceReportUseCase.getRaceReport(id);
    }

    @Override
    public List<F1RaceReport> getALlRaceReports() {
        return f1RaceReportUseCase.getALlRaceReports();
    }

    @Override
    public Optional<F1RaceReport> getRaceReportByDate(ZonedDateTime date) {
        return f1RaceReportUseCase.getRaceReportByDate(date);
    }

    @Override
    public Optional<F1RaceReport> getRaceReportByRaceId(String eventId) {
        return f1RaceReportUseCase.getRaceReportByRaceId(eventId);
    }

    @Override
    public Optional<List<Long>> getDrivers() {
        return f1RaceReportUseCase.getDrivers();
    }
}
