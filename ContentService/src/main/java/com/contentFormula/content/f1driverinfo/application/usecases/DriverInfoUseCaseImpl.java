package com.contentFormula.content.f1driverinfo.application.usecases;

import com.contentFormula.content.f1driverinfo.domain.model.DriverInfo;
import com.contentFormula.content.f1driverinfo.domain.port.in.DriverInfoUseCase;
import com.contentFormula.content.f1driverinfo.domain.port.out.DriverInfoRepositoryPort;
import lombok.AllArgsConstructor;


import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class DriverInfoUseCaseImpl implements DriverInfoUseCase {

     private final DriverInfoRepositoryPort driverInfoRepositoryPort;


    @Override
    public Optional<DriverInfo> createDriverInfo(DriverInfo driverInfo) {
        return driverInfoRepositoryPort.saveDriverInfo(driverInfo);
    }

    @Override
    public Optional<DriverInfo> getDriverInfo(Long driverId) {
        return driverInfoRepositoryPort.getDriverInfo(driverId);
    }

    @Override
    public List<DriverInfo> getAllDrivers() {
        return driverInfoRepositoryPort.getAllDrivers();
    }

    @Override
    public Optional<DriverInfo> getDriverByName(String driverName) {
        return driverInfoRepositoryPort.getByName(driverName);
    }

    @Override
    public List<DriverInfo> getDriversPerTeam(String team) {
        return driverInfoRepositoryPort.getDriversByTeam(team);
    }

    @Override
    public List<Long> getAllDriverIds() {
        return driverInfoRepositoryPort.getAllDriverIds();
    }

    @Override
    public void updateDriverInfoInDB(Long driverId, DriverInfo driverInfo) {
        driverInfoRepositoryPort.updateDriverInfo(driverId,driverInfo);
    }
}
