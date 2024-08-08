package com.contentFormula.content.f1racereport.infraestructure.adapters.secondary;


import com.contentFormula.content.f1racereport.infraestructure.entities.F1RaceReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaF1RaceReportRepository extends JpaRepository<F1RaceReportEntity,Long> {
}
