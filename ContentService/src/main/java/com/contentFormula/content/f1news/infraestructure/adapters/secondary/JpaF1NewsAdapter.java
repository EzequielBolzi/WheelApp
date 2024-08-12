package com.contentFormula.content.f1news.infraestructure.adapters.secondary;


import com.contentFormula.content.f1news.domain.model.F1News;
import com.contentFormula.content.f1news.domain.port.out.F1NewsRepositoryPort;
import com.contentFormula.content.f1news.infraestructure.entities.F1NewsEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class JpaF1NewsAdapter implements F1NewsRepositoryPort{

    private final JpaF1NewsRepository jpaF1NewsRepository;

    @Override
    public Optional<F1News> save(F1News f1News) {
        F1NewsEntity f1NewsEntity = F1NewsMapper.toEntity(f1News);
        F1NewsEntity savedF1NewsEntity = jpaF1NewsRepository.save(f1NewsEntity);
        return Optional.of(F1NewsMapper.toDomain(savedF1NewsEntity));
    }

    @Override
    public Optional<List<F1News>> getNewsFromADriver(String driverName) {
        Optional<List<F1NewsEntity>> news = jpaF1NewsRepository.findNewsByName(driverName);
        return news.map(entities -> entities.stream()
                .map(F1NewsMapper::toDomain)
                .collect(Collectors.toList()));
    }




    @Override
    public List<F1News> getAllNews() {
        List<F1NewsEntity> allNews = jpaF1NewsRepository.findAll();
        return allNews.stream()
                .map(F1NewsMapper::toDomain)
                .collect(Collectors.toList());
    }
}
