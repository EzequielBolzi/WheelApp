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
    public List<DriverRaceResult> getResultsByFullName(String fullName) {
        return driverRaceResultUseCase.getResultsByFullName(fullName);
    }

    @Override
    public Optional<DriverRaceResult> getByRaceIdAndDriverInfoId(String raceName, Long driverId) {
        return driverRaceResultUseCase.getByRaceIdAndDriverInfoId(raceName,driverId);
    }


}
