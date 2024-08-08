package com.contentFormula.content.f1driverinfo.application.service;

import com.contentFormula.content.f1driverinfo.domain.model.DriverInfo;
import com.contentFormula.content.f1driverinfo.domain.port.in.DriverInfoUseCase;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
public class DriverInfoService  implements DriverInfoUseCase {

    private final DriverInfoUseCase driverInfoUseCase;

    @Override
    public Optional<DriverInfo> createDriverInfo(DriverInfo driverInfo) {
        return driverInfoUseCase.createDriverInfo(driverInfo);
    }

    @Override
    public Optional<DriverInfo> getDriverInfo(Long driverId) {
        return driverInfoUseCase.getDriverInfo(driverId);
    }

    @Override
    public List<DriverInfo> getAllDrivers() {
        return driverInfoUseCase.getAllDrivers();
    }

    @Override
    public Optional<DriverInfo> getDriverByName(String driverName) {
        return driverInfoUseCase.getDriverByName(driverName);
    }

    @Override
    public List<DriverInfo> getDriversPerTeam(String team) {
        return driverInfoUseCase.getDriversPerTeam(team);
    }


}
