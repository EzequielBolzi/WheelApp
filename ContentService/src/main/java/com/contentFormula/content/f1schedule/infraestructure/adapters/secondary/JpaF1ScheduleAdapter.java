package com.contentFormula.content.f1schedule.infraestructure.adapters.secondary;

import com.contentFormula.content.f1racereport.infraestructure.adapters.secondary.F1RaceReportMapper;
import com.contentFormula.content.f1racereport.infraestructure.adapters.secondary.JpaF1RaceReportRepository;
import com.contentFormula.content.f1racereport.infraestructure.entities.F1RaceReportEntity;
import com.contentFormula.content.f1schedule.domain.model.F1Schedule;
import com.contentFormula.content.f1schedule.domain.port.out.F1ScheduleRepositoryPort;
import com.contentFormula.content.f1schedule.infraestructure.entities.F1ScheduleEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class JpaF1ScheduleAdapter implements F1ScheduleRepositoryPort {

    private final JpaF1ScheduleRepository jpaF1ScheduleRepository;

    @Override
    public Optional<F1Schedule> saveSchedule(F1Schedule f1Schedule) {
        F1ScheduleEntity f1ScheduleEntity = F1ScheduleMapper.toEntity(f1Schedule);
        F1ScheduleEntity savedF1ScheduleEntity = jpaF1ScheduleRepository.save(f1ScheduleEntity);
        return Optional.of(F1ScheduleMapper.toDomain(savedF1ScheduleEntity));
    }

    @Override
    public List<F1Schedule> getScheduleByYear(Integer year) {
        return jpaF1ScheduleRepository.findByYear(year).stream().map(F1ScheduleMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<F1Schedule> getAllSchedule() {
        return jpaF1ScheduleRepository.findAll()
                .stream()
                .map(F1ScheduleMapper::toDomain)
                .collect(Collectors.toList());
    }
}
