package com.contentFormula.content.driverinfo.infraestructure.config;

import com.contentFormula.content.driverinfo.application.service.DriverInfoService;
import com.contentFormula.content.driverinfo.application.usecases.DriverInfoUseCaseImpl;
import com.contentFormula.content.driverinfo.domain.port.in.DriverInfoUseCase;
import com.contentFormula.content.driverinfo.domain.port.out.DriverInfoRepositoryPort;
import com.contentFormula.content.driverinfo.infraestructure.adapters.secondary.JpaDriverInfoAdapter;
import com.contentFormula.content.driverinfo.infraestructure.adapters.secondary.JpaDriverInfoRepository;
import com.contentFormula.content.driverinfo.infraestructure.adapters.secondary.JpaVehicleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
public class DriverConfig {

    @Bean
    public HttpClient httpClient() {
        return HttpClient.newHttpClient();
    }


    @Bean
    public JpaDriverInfoAdapter jpaDriverInfoAdapter(JpaDriverInfoRepository jpaDriverInfoRepository, JpaVehicleRepository jpaVehicleRepository) {
        return new JpaDriverInfoAdapter(jpaDriverInfoRepository,jpaVehicleRepository);
    }


    @Bean
    public DriverInfoUseCase driverInfoUseCase(DriverInfoRepositoryPort driverInfoRepositoryPort) {
        return new DriverInfoUseCaseImpl(driverInfoRepositoryPort);
    }

    @Bean
    public DriverInfoService driverInfoService(DriverInfoUseCase driverInfoUseCase) {
        return new DriverInfoService(driverInfoUseCase);
    }
}