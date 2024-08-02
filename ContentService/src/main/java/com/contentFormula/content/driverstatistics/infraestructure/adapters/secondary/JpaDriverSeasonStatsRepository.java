package com.contentFormula.content.driverstatistics.infraestructure.adapters.secondary;

import com.contentFormula.content.driverstatistics.infraestructure.entities.DriverSeasonStatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaDriverSeasonStatsRepository extends JpaRepository<DriverSeasonStatsEntity, Long> {


    @Query("SELECT dss FROM DriverSeasonStatsEntity dss " +
        "JOIN dss.driverInfo di " +
        "WHERE dss.year = :year AND di.driverIdentifier = :driverInfoId")
    Optional<DriverSeasonStatsEntity> findByYearAndDriverInfo_DriverIdentifier(Integer year, Long driverInfoId);


}

