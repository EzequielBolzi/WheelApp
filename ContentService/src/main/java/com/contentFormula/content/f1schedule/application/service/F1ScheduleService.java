package com.contentFormula.content.f1schedule.application.service;

import com.contentFormula.content.f1schedule.domain.model.F1Schedule;
import com.contentFormula.content.f1schedule.domain.port.in.F1ScheduleUseCase;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
public class F1ScheduleService implements F1ScheduleUseCase {

    private final F1ScheduleUseCase f1ScheduleUseCase;

    @Override
    public Optional<F1Schedule> createSchedule(F1Schedule f1Schedule) {
        return f1ScheduleUseCase.createSchedule(f1Schedule);
    }

    @Override
    public Optional<List<F1Schedule>> getScheduleByYear(Integer year) {
        return f1ScheduleUseCase.getScheduleByYear(year);
    }

    @Override
    public Optional<List<F1Schedule>> getAllSchedule() {
        return f1ScheduleUseCase.getAllSchedule();
    }
}
