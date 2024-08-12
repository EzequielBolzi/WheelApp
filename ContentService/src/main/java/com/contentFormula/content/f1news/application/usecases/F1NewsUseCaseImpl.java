package com.contentFormula.content.f1news.application.usecases;

import com.contentFormula.content.f1news.domain.model.F1News;
import com.contentFormula.content.f1news.domain.port.in.F1NewsUseCase;
import com.contentFormula.content.f1news.domain.port.out.F1NewsRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor

public class F1NewsUseCaseImpl implements F1NewsUseCase {

    private final F1NewsRepositoryPort f1NewsRepositoryPort;

    @Override
    public Optional<F1News> createF1News(F1News f1News) {
        return f1NewsRepositoryPort.save(f1News);
    }

    @Override
    public Optional<List<F1News>> getNewsFromADriver(String driverName) {
        return f1NewsRepositoryPort.getNewsFromADriver(driverName);
    }

    @Override
    public List<F1News> getAllNews() {
        return f1NewsRepositoryPort.getAllNews();
    }
}
