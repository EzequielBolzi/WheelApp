package com.contentFormula.content.f1racereport.infraestructure.config;


import com.contentFormula.content.f1racereport.application.service.F1RaceReportService;
import com.contentFormula.content.f1racereport.application.usecases.F1RaceReportUseCaseImpl;
import com.contentFormula.content.f1racereport.domain.port.in.F1RaceReportUseCase;
import com.contentFormula.content.f1racereport.domain.port.out.F1RaceReportRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class F1RaceReportConfig {

    @Bean
    public F1RaceReportUseCase f1RaceReportUseCase(F1RaceReportRepositoryPort f1RaceRepositoryPort) {
        return new F1RaceReportUseCaseImpl(f1RaceRepositoryPort);
    }

    @Bean
    public F1RaceReportService f1RaceReportService(F1RaceReportUseCase f1RaceReportUseCase) {
        return new F1RaceReportService(f1RaceReportUseCase);
    }
}
