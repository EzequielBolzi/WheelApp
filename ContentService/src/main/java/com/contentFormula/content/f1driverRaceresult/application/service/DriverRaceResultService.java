package com.contentFormula.content.f1driverRaceresult.application.service;

import com.contentFormula.content.f1driverRaceresult.domain.model.DriverRaceResult;
import com.contentFormula.content.f1driverRaceresult.domain.port.in.DriverRaceResultUseCase;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class DriverRaceResultService implements DriverRaceResultUseCase {

    private final DriverRaceResultUseCase driverRaceResultUseCase;

    @Override
    public Optional<DriverRaceResult> createDriverRaceResult(DriverRaceResult driverRaceResult) {
        return driverRaceResultUseCase.createDriverRaceResult(driverRaceResult);
    }

    @Override
    public List<DriverRaceResult> findResultsByFullName(String fullName) {
        return driverRaceResultUseCase.findResultsByFullName(fullName);
    }

    @Override
    public Optional<DriverRaceResult> findByRaceIdAndDriverInfoId(String raceName, Long driverId) {
        return driverRaceResultUseCase.findByRaceIdAndDriverInfoId(raceName,driverId);
    }


}
