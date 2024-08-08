package com.contentFormula.content.f1racereport.application.service;

import com.contentFormula.content.f1racereport.domain.model.F1RaceReport;
import com.contentFormula.content.f1racereport.domain.port.in.F1RaceReportUseCase;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class F1RaceReportService implements F1RaceReportUseCase {

    private final F1RaceReportUseCase f1RaceReportUseCase;

    @Override
    public Optional<F1RaceReport> createRaceReport(F1RaceReport f1RaceReport) {
        return f1RaceReportUseCase.createRaceReport(f1RaceReport);
    }

    @Override
    public Optional<F1RaceReport> getRaceReport(Long id) {
        return f1RaceReportUseCase.getRaceReport(id);
    }

    @Override
    public List<F1RaceReport> getALlRaceReports() {
        return f1RaceReportUseCase.getALlRaceReports();
    }
}
