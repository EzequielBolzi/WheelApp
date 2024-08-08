package com.contentFormula.content.f1driverRaceresult.domain.port.in;

import com.contentFormula.content.f1driverRaceresult.domain.model.DriverRaceResult;

import java.util.List;
import java.util.Optional;

public interface DriverRaceResultUseCase {
    Optional<DriverRaceResult> createDriverRaceResult (DriverRaceResult driverRaceResult);
    List<DriverRaceResult> findResultsByFullName(String fullName);
    Optional<DriverRaceResult> findByRaceIdAndDriverInfoId(String raceName, Long driverId);

}
