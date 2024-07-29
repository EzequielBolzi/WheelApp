package com.contentFormula.content.apicontent.infraestructure.config;

import com.contentFormula.content.apicontent.application.service.DriverInfoService;
import com.contentFormula.content.apicontent.application.usecases.DriverInfoUseCaseImpl;
import com.contentFormula.content.apicontent.domain.port.in.DriverInfoUseCase;
import com.contentFormula.content.apicontent.domain.port.out.DriverInfoRepositoryPort;
import com.contentFormula.content.apicontent.infraestructure.adapters.secondary.JpaDriverInfoAdapter;
import com.contentFormula.content.apicontent.infraestructure.adapters.secondary.JpaDriverInfoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
public class ServiceAppConfig {

    @Bean
    public HttpClient httpClient() {
        return HttpClient.newHttpClient();
    }


    @Bean
    public JpaDriverInfoAdapter jpaDriverInfoAdapter(JpaDriverInfoRepository jpaDriverInfoRepository) {
        return new JpaDriverInfoAdapter(jpaDriverInfoRepository);
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