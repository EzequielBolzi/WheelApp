package com.contentFormula.content.f1schedule.domain.port.out;

import com.contentFormula.content.f1schedule.domain.model.F1Schedule;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface F1ScheduleRepositoryPort {
    Optional<F1Schedule> saveSchedule(F1Schedule f1Schedule);
    List<F1Schedule> getScheduleByYear(Integer year);
    List<F1Schedule> getAllSchedule();
    Optional<F1Schedule> getScheduleByDate(ZonedDateTime date);
    void updateSchedule(F1Schedule f1Schedule, Long id);
}
