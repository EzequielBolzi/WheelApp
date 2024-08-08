package com.contentFormula.content.f1schedule.domain.port.in;

import com.contentFormula.content.f1schedule.domain.model.F1Schedule;

import java.util.List;
import java.util.Optional;

public interface F1ScheduleUseCase {
    Optional<F1Schedule> createSchedule(F1Schedule f1Schedule);
    Optional<List<F1Schedule>> getScheduleByYear(Integer year);
    Optional<List<F1Schedule>> getAllSchedule();
}
