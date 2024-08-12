package com.contentFormula.content.f1driverstatistics.application.usecases;


import com.contentFormula.content.f1driverstatistics.domain.model.DriverSeasonStats;
import com.contentFormula.content.f1driverstatistics.domain.port.in.DriverSeasonStatsUseCase;
import com.contentFormula.content.f1driverstatistics.domain.port.out.DriverSeasonStatsRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class DriverSeasonStatsUseCaseImpl implements DriverSeasonStatsUseCase {

    private final DriverSeasonStatsRepositoryPort driverSeasonStatsRepositoryPort;


    @Override
    public Optional<DriverSeasonStats> createDriverSeasonStats(DriverSeasonStats driverSeasonStats) {
        return driverSeasonStatsRepositoryPort.save(driverSeasonStats);
    }


    @Override
    public Optional<DriverSeasonStats> getByYearAndDriverInfo_Id(Integer year, Long driverInfoId) {
        return driverSeasonStatsRepositoryPort.getByYearAndDriverInfo_Id(year,driverInfoId);
    }
}
