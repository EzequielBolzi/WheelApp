package com.contentFormula.content.f1schedule.application.usecases;


import com.contentFormula.content.f1schedule.domain.model.F1Schedule;
import com.contentFormula.content.f1schedule.domain.port.in.F1ScheduleUseCase;
import com.contentFormula.content.f1schedule.domain.port.out.F1ScheduleRepositoryPort;
import lombok.AllArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
            public class F1ScheduleUseCaseImpl implements F1ScheduleUseCase {

    private final F1ScheduleRepositoryPort f1ScheduleRepositoryPort;

    @Override
    public Optional<F1Schedule> createSchedule(F1Schedule f1Schedule) {
        return f1ScheduleRepositoryPort.saveSchedule(f1Schedule);
    }



    @Override
    public Optional<List<F1Schedule>> getScheduleByYear(Integer year) {
        return Optional.ofNullable(f1ScheduleRepositoryPort.getScheduleByYear(year));
    }

    @Override
    public Optional<List<F1Schedule>> getAllSchedule() {
        return Optional.ofNullable(f1ScheduleRepositoryPort.getAllSchedule());
    }

    @Override
    public Optional<Optional<F1Schedule>> getScheduleByDate(ZonedDateTime date) {
        return Optional.of(f1ScheduleRepositoryPort.getScheduleByDate(date));
    }

    @Override
    public void updateSchedule(F1Schedule f1Schedule, Long id) {
        f1ScheduleRepositoryPort.updateSchedule(f1Schedule,id);
    }


}
