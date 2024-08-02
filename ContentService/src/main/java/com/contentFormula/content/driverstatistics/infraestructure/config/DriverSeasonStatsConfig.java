package com.contentFormula.content.driverstatistics.infraestructure.config;

import com.contentFormula.content.driverstatistics.application.service.DriverSeasonStatsService;
import com.contentFormula.content.driverstatistics.application.usecases.DriverSeasonStatsUseCaseImpl;
import com.contentFormula.content.driverstatistics.domain.port.in.DriverSeasonStatsUseCase;
import com.contentFormula.content.driverstatistics.domain.port.out.DriverSeasonStatsRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class DriverSeasonStatsConfig {

    @Bean
    public DriverSeasonStatsUseCase driverSeasonStatsUseCase(DriverSeasonStatsRepositoryPort repositoryPort) {
        return new DriverSeasonStatsUseCaseImpl(repositoryPort);
    }

    @Bean
    public DriverSeasonStatsService driverSeasonStatsService(DriverSeasonStatsUseCase driverSeasonStatsUseCase) {
        return new DriverSeasonStatsService(driverSeasonStatsUseCase);
    }
}
