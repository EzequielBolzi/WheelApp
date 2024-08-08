package com.contentFormula.content.f1driverRaceresult.infraestructure.config;

import com.contentFormula.content.f1driverRaceresult.application.service.DriverRaceResultService;
import com.contentFormula.content.f1driverRaceresult.domain.port.in.DriverRaceResultUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class RaceResultConfig {
    @Bean
    public DriverRaceResultService driverRaceResultService(@Lazy DriverRaceResultUseCase driverRaceResultUseCase) {
        return new DriverRaceResultService(driverRaceResultUseCase);
    }
}
