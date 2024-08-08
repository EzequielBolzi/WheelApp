package com.contentFormula.content.f1news.infraestructure.adapters.secondary;

import com.contentFormula.content.f1news.infraestructure.entities.F1NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaF1NewsRepository extends JpaRepository<F1NewsEntity,Long> {

    @Query("SELECT n FROM F1NewsEntity n WHERE n.description LIKE %:driverName%" +
            " OR n.headline LIKE %:driverName% " +
            "OR n.link LIKE %:driverName% ")
    Optional<List<F1NewsEntity>> findNewsByName(@Param("driverName") String driverName);
    Optional<F1NewsEntity> findByDataSourceIdentifier(String dataSourceIdentifier);
}