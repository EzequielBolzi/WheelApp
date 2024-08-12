package com.contentFormula.content.f1driverstatistics.domain.port.out;

import com.contentFormula.content.f1driverstatistics.domain.model.DriverSeasonStats;

import java.util.Optional;

public interface DriverSeasonStatsRepositoryPort {
    Optional<DriverSeasonStats> save(DriverSeasonStats driverSeasonStats);
/*
    List<DriverSeasonStats> findResultsByFullName(String fullName);
*/
    Optional<DriverSeasonStats> getByYearAndDriverInfo_Id(Integer year, Long driverInfoId);

}
