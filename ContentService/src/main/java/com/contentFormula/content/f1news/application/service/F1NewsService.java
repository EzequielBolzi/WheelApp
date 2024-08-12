package com.contentFormula.content.f1news.application.service;

import com.contentFormula.content.f1news.domain.model.F1News;
import com.contentFormula.content.f1news.domain.port.in.F1NewsUseCase;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class F1NewsService implements F1NewsUseCase {

    private final F1NewsUseCase f1NewsUseCase;

    @Override
    public Optional<F1News> createF1News(F1News f1News) {
        return f1NewsUseCase.createF1News(f1News);
    }

    @Override
    public Optional<List<F1News>> getNewsFromADriver(String driverName) {
        return f1NewsUseCase.getNewsFromADriver(driverName);
    }

    @Override
    public List<F1News> getAllNews() {
        return f1NewsUseCase.getAllNews();
    }
}
