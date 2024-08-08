package com.contentFormula.content.f1racereport.infraestructure.adapters.secondary;


import com.contentFormula.content.f1racereport.domain.model.F1RaceReport;
import com.contentFormula.content.f1racereport.domain.port.out.F1RaceReportRepositoryPort;
import com.contentFormula.content.f1racereport.infraestructure.entities.F1RaceReportEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class JpaF1RaceReportAdapter implements F1RaceReportRepositoryPort {


    private final JpaF1RaceReportRepository jpaF1RaceReportRepository;

    @Override
    public Optional<F1RaceReport> saveRaceReport(F1RaceReport f1RaceReport) {
        F1RaceReportEntity f1RaceReportEntity = F1RaceReportMapper.toEntity(f1RaceReport);
        F1RaceReportEntity savedF1RaceReportEntity = jpaF1RaceReportRepository.save(f1RaceReportEntity);
        return Optional.of(F1RaceReportMapper.toDomain(savedF1RaceReportEntity));
    }

    @Override
    public List<F1RaceReport> getAllRaceReports() {
        return jpaF1RaceReportRepository.findAll()
                .stream()
                .map(F1RaceReportMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<F1RaceReport> getRaceReport(Long id) {
        return jpaF1RaceReportRepository.findById(id).map(F1RaceReportMapper::toDomain);
    }
}
