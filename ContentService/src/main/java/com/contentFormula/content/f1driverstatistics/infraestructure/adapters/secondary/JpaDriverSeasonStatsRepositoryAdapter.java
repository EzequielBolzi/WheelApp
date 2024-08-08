package com.contentFormula.content.f1driverstatistics.infraestructure.adapters.secondary;


import com.contentFormula.content.f1driverstatistics.domain.model.DriverSeasonStats;
import com.contentFormula.content.f1driverstatistics.domain.port.out.DriverSeasonStatsRepositoryPort;
import com.contentFormula.content.f1driverstatistics.infraestructure.entities.DriverSeasonStatsEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;




@AllArgsConstructor
@Component
public class JpaDriverSeasonStatsRepositoryAdapter implements DriverSeasonStatsRepositoryPort {

    @Autowired
    private final JpaDriverSeasonStatsRepository jpaDriverSeasonStatsRepository;

    @Override
    public Optional<DriverSeasonStats> save(DriverSeasonStats driverSeasonStats) {
        DriverSeasonStatsEntity entity = DriverSeasonStatsMapper.toEntity(driverSeasonStats);
        DriverSeasonStatsEntity savedEntity = jpaDriverSeasonStatsRepository.save(entity);
        return Optional.of(DriverSeasonStatsMapper.toDomain(savedEntity));
    }

    @Override
    public Optional<DriverSeasonStats> findByYearAndDriverInfo_Id(Integer year, Long driverIdentifier) {
        return jpaDriverSeasonStatsRepository.findByYearAndDriverInfo_DriverIdentifier(year, driverIdentifier).map(DriverSeasonStatsMapper::toDomain);
    }
}
