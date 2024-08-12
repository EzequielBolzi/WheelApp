package com.contentFormula.content.f1news.domain.port.in;

import com.contentFormula.content.f1news.domain.model.F1News;

import java.util.List;
import java.util.Optional;


public interface F1NewsUseCase {
    Optional<F1News> createF1News (F1News f1News);
    Optional<List<F1News>> getNewsFromADriver(String driverName);
    List<F1News> getAllNews( );

}
