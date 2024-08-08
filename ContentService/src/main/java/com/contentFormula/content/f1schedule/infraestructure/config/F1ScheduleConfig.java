package com.contentFormula.content.f1schedule.infraestructure.config;

import com.contentFormula.content.f1schedule.application.service.F1ScheduleService;
import com.contentFormula.content.f1schedule.application.usecases.F1ScheduleUseCaseImpl;
import com.contentFormula.content.f1schedule.domain.port.in.F1ScheduleUseCase;
import com.contentFormula.content.f1schedule.domain.port.out.F1ScheduleRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class F1ScheduleConfig {

    @Bean
    public F1ScheduleUseCase f1ScheduleUseCase(F1ScheduleRepositoryPort repositoryPort) {
        return new F1ScheduleUseCaseImpl(repositoryPort);
    }

    @Bean
    public F1ScheduleService f1ScheduleService(F1ScheduleUseCase f1ScheduleUseCase) {
        return new F1ScheduleService(f1ScheduleUseCase);
    }
}
