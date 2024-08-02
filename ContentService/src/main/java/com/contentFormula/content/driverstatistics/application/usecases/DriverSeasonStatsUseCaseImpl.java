package com.contentFormula.content.driverstatistics.application.usecases;


import com.contentFormula.content.driverstatistics.domain.model.DriverSeasonStats;
import com.contentFormula.content.driverstatistics.domain.port.in.DriverSeasonStatsUseCase;
import com.contentFormula.content.driverstatistics.domain.port.out.DriverSeasonStatsRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class DriverSeasonStatsUseCaseImpl implements DriverSeasonStatsUseCase {

    private final DriverSeasonStatsRepositoryPort driverSeasonStatsRepositoryPort;


    @Override
    public Optional<DriverSeasonStats> createDriverSeasonStats(DriverSeasonStats driverSeasonStats) {
        return driverSeasonStatsRepositoryPort.save(driverSeasonStats);
    }


    @Override
    public Optional<DriverSeasonStats> findByYearAndDriverInfo_Id(Integer year, Long driverInfoId) {
        return driverSeasonStatsRepositoryPort.findByYearAndDriverInfo_Id(year,driverInfoId);
    }
}
