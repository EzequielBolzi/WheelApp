package com.contentFormula.content.driverstatistics.domain.port.in;

import com.contentFormula.content.driverstatistics.domain.model.DriverSeasonStats;

import java.util.Optional;

public interface DriverSeasonStatsUseCase {
    Optional<DriverSeasonStats> createDriverSeasonStats (DriverSeasonStats driverSeasonStats);

    Optional<DriverSeasonStats> findByYearAndDriverInfo_Id(Integer year, Long driverInfoId);

}

