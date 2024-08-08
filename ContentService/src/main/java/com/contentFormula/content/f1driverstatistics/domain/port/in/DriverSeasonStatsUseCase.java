package com.contentFormula.content.f1driverstatistics.domain.port.in;

import com.contentFormula.content.f1driverstatistics.domain.model.DriverSeasonStats;

import java.util.Optional;

public interface DriverSeasonStatsUseCase {
    Optional<DriverSeasonStats> createDriverSeasonStats (DriverSeasonStats driverSeasonStats);

    Optional<DriverSeasonStats> findByYearAndDriverInfo_Id(Integer year, Long driverInfoId);

}

