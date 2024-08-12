package com.contentFormula.content.f1schedule.infraestructure.adapters.secondary;

import com.contentFormula.content.f1schedule.infraestructure.entities.F1ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.ZonedDateTime;
import java.util.List;

public interface JpaF1ScheduleRepository extends JpaRepository<F1ScheduleEntity, Long> {

    @Query("SELECT fse FROM F1ScheduleEntity fse " +
            "WHERE YEAR(fse.startDate) = :year ")
    List<F1ScheduleEntity> findByYear(Integer year);

    @Query("SELECT fse FROM F1ScheduleEntity fse WHERE fse.startDate = :date")
    F1ScheduleEntity findScheduleByDate(ZonedDateTime date);

}
