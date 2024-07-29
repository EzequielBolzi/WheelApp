package com.contentFormula.content.apicontent.application.service;

import com.contentFormula.content.apicontent.domain.dtos.DriverInfoDto;
import com.contentFormula.content.apicontent.domain.model.DriverInfo;
import com.contentFormula.content.apicontent.domain.port.in.DriverInfoUseCase;
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
    public Optional<DriverInfo> updateDriver(Long driverId, DriverInfo driverInfo) {
        return driverInfoUseCase.updateDriver(driverId,driverInfo);
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
    public List<DriverInfo> getDriversByTeam(String team) {
        return driverInfoUseCase.getDriversByTeam(team);
    }


}
