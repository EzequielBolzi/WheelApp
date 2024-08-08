package com.contentFormula.content.f1driverstatistics.infraestructure.config;

import com.contentFormula.content.f1driverinfo.infraestructure.adapters.secondary.JpaDriverInfoAdapter;
import com.contentFormula.content.f1driverinfo.infraestructure.adapters.secondary.JpaDriverInfoRepository;
import com.contentFormula.content.f1driverinfo.infraestructure.adapters.secondary.JpaVehicleRepository;
import com.contentFormula.content.f1driverstatistics.application.service.DriverSeasonStatsService;
import com.contentFormula.content.f1driverstatistics.application.usecases.DriverSeasonStatsUseCaseImpl;
import com.contentFormula.content.f1driverstatistics.domain.port.in.DriverSeasonStatsUseCase;
import com.contentFormula.content.f1driverstatistics.domain.port.out.DriverSeasonStatsRepositoryPort;
import com.contentFormula.content.f1schedule.infraestructure.adapters.secondary.JpaF1ScheduleAdapter;
import com.contentFormula.content.f1schedule.infraestructure.adapters.secondary.JpaF1ScheduleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

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
    @Bean
    public JpaF1ScheduleAdapter jpaF1ScheduleAdapter(JpaF1ScheduleRepository jpaF1ScheduleRepository) {
        return new JpaF1ScheduleAdapter(jpaF1ScheduleRepository);
    }

}
