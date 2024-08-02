package com.contentFormula.content.driverstatistics.application.service;

import com.contentFormula.content.driverstatistics.domain.model.DriverSeasonStats;
import com.contentFormula.content.driverstatistics.domain.port.in.DriverSeasonStatsUseCase;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class DriverSeasonStatsService implements DriverSeasonStatsUseCase {

    private final DriverSeasonStatsUseCase driverSeasonStatsUseCase;

    @Override
    public Optional<DriverSeasonStats> createDriverSeasonStats(DriverSeasonStats driverSeasonStats) {
        return driverSeasonStatsUseCase.createDriverSeasonStats(driverSeasonStats);
    }



    @Override
    public Optional<DriverSeasonStats> findByYearAndDriverInfo_Id(Integer year, Long driverInfoId) {
        return driverSeasonStatsUseCase.findByYearAndDriverInfo_Id(year,driverInfoId);
    }


}
