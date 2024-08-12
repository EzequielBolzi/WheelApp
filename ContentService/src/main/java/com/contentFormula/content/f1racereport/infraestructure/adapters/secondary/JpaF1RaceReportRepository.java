package com.contentFormula.content.f1racereport.infraestructure.adapters.secondary;


import com.contentFormula.content.f1driverinfo.infraestructure.entities.DriverInfoEntity;
import com.contentFormula.content.f1racereport.domain.model.F1RaceReport;
import com.contentFormula.content.f1racereport.infraestructure.entities.F1DriverPositionEntity;
import com.contentFormula.content.f1racereport.infraestructure.entities.F1RaceReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface JpaF1RaceReportRepository extends JpaRepository<F1RaceReportEntity,Long> {
    @Query("SELECT frr FROM F1RaceReportEntity frr WHERE frr.startDate = :date")
    F1RaceReportEntity getRaceReportByDate(ZonedDateTime date);

    @Query("SELECT frr FROM F1RaceReportEntity frr WHERE frr.raceEventId = :eventId")
    F1RaceReportEntity getRaceReportByRaceId(@Param("eventId") String eventId);

    @Query("SELECT DISTINCT fde.driverInfo FROM F1DriverPositionEntity fde")
    List<Long> getDrivers();

}
