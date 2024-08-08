package com.contentFormula.content.f1news.infraestructure.config;


import com.contentFormula.content.f1news.application.service.F1NewsService;
import com.contentFormula.content.f1news.application.usecases.F1NewsUseCaseImpl;
import com.contentFormula.content.f1news.domain.port.in.F1NewsUseCase;
import com.contentFormula.content.f1news.domain.port.out.F1NewsRepositoryPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class F1NewsConfig {


    @Bean
    public F1NewsUseCase f1NewsUseCase(F1NewsRepositoryPort repositoryPort) {
        return new F1NewsUseCaseImpl(repositoryPort);
    }

    @Bean
    public F1NewsService f1NewsService(F1NewsUseCase f1NewsUseCase) {
        return new F1NewsService(f1NewsUseCase);
    }

}
