package com.contentFormula.content.f1news.domain.port.out;

import com.contentFormula.content.f1news.domain.model.F1News;

import java.util.List;
import java.util.Optional;

public interface F1NewsRepositoryPort {
    Optional<F1News> save(F1News f1News);
    Optional<List<F1News>> getNewsFromADriver(String driverName);
    Optional<List<F1News>> getAllNews( );
}
