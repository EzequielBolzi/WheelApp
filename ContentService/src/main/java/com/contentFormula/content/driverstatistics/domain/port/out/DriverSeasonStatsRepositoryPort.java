package com.contentFormula.content.driverstatistics.domain.port.out;

import com.contentFormula.content.driverstatistics.domain.model.DriverSeasonStats;

import java.util.List;
import java.util.Optional;

public interface DriverSeasonStatsRepositoryPort {
    Optional<DriverSeasonStats> save(DriverSeasonStats driverSeasonStats);
/*
    List<DriverSeasonStats> findResultsByFullName(String fullName);
*/
    Optional<DriverSeasonStats> findByYearAndDriverInfo_Id(Integer year, Long driverInfoId);

}
