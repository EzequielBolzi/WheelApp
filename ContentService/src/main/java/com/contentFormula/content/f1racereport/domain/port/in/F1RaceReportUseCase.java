package com.contentFormula.content.f1racereport.domain.port.in;

import com.contentFormula.content.f1racereport.domain.model.F1RaceReport;

import java.util.List;
import java.util.Optional;

public interface F1RaceReportUseCase {
    Optional<F1RaceReport> createRaceReport(F1RaceReport f1RaceReport);
    Optional<F1RaceReport> getRaceReport(Long id);
    List<F1RaceReport> getALlRaceReports();

}
