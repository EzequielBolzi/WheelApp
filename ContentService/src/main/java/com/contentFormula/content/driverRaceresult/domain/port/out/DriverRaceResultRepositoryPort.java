package com.contentFormula.content.driverRaceresult.domain.port.out;

import com.contentFormula.content.driverRaceresult.domain.model.DriverRaceResult;

import java.util.List;
import java.util.Optional;

public interface DriverRaceResultRepositoryPort {
    Optional<DriverRaceResult> save(DriverRaceResult driverRaceResult);
    Optional<DriverRaceResult> findById(Long id);
    Optional<DriverRaceResult> findByDate(String date);
    List<DriverRaceResult> findResultsByFullName(String fullName);
    Optional<DriverRaceResult> findByRaceNameAndDriverInfoId(String raceName, Long driverId);

}
