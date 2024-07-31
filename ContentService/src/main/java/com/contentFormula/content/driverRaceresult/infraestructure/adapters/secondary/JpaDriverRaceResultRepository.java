package com.contentFormula.content.driverRaceresult.infraestructure.adapters.secondary;

import com.contentFormula.content.driverRaceresult.domain.model.DriverRaceResult;
import com.contentFormula.content.driverRaceresult.infraestructure.entities.DriverRaceResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaDriverRaceResultRepository extends JpaRepository<DriverRaceResultEntity, Long> {

    Optional<DriverRaceResultEntity> findByDate(String date);
    @Query("SELECT drr FROM DriverRaceResultEntity drr WHERE LOWER(drr.driverInfo.fullName) LIKE LOWER(CONCAT('%', :fullName, '%'))")
    List<DriverRaceResultEntity> findByDriverName(String fullName);

    // New query to find by race name and driver id
    @Query("SELECT drr FROM DriverRaceResultEntity drr WHERE LOWER(drr.race) LIKE LOWER(:raceName) AND drr.driverInfo.id = :driverId")
    Optional<DriverRaceResultEntity> findByRaceNameAndDriverId(String raceName, Long driverId);


}
