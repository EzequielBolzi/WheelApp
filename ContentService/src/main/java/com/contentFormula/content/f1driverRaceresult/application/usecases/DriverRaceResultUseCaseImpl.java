package com.contentFormula.content.f1driverRaceresult.application.usecases;

import com.contentFormula.content.f1driverRaceresult.domain.model.DriverRaceResult;
import com.contentFormula.content.f1driverRaceresult.domain.port.in.DriverRaceResultUseCase;
import com.contentFormula.content.f1driverRaceresult.domain.port.out.DriverRaceResultRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class DriverRaceResultUseCaseImpl implements DriverRaceResultUseCase {

    private final DriverRaceResultRepositoryPort driverInfoRepositoryPort;

    @Override
    public Optional<DriverRaceResult> createDriverRaceResult(DriverRaceResult driverRaceResult) {
        return driverInfoRepositoryPort.save(driverRaceResult);
    }

    @Override
    public List<DriverRaceResult> getResultsByFullName(String fullName) {
        return driverInfoRepositoryPort.findResultsByFullName(fullName);
    }

    @Override
    public Optional<DriverRaceResult> getByRaceIdAndDriverInfoId(String raceName, Long driverId) {
        return driverInfoRepositoryPort.findByRaceNameAndDriverInfoId(raceName,driverId);
    }


}
