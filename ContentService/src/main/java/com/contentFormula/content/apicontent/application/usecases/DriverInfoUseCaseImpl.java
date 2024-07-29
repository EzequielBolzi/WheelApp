package com.contentFormula.content.apicontent.application.usecases;

import com.contentFormula.content.apicontent.domain.dtos.DriverInfoDto;
import com.contentFormula.content.apicontent.domain.model.DriverInfo;
import com.contentFormula.content.apicontent.domain.port.in.DriverInfoUseCase;
import com.contentFormula.content.apicontent.domain.port.out.DriverInfoRepositoryPort;
import lombok.AllArgsConstructor;


import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class DriverInfoUseCaseImpl implements DriverInfoUseCase {

     private final DriverInfoRepositoryPort driverInfoRepositoryPort;




    @Override
    public Optional<DriverInfo> createDriverInfo(DriverInfo driverInfo) {
        return driverInfoRepositoryPort.saveDriverInfoInDB(driverInfo);
    }

    @Override
    public Optional<DriverInfo> updateDriver(Long driverId, DriverInfo driverInfo) {
        return driverInfoRepositoryPort.updateDriverInfoInDB(driverId,driverInfo);
    }

    @Override
    public Optional<DriverInfo> getDriverInfo(Long driverId) {
        return driverInfoRepositoryPort.getDriverInfoFromDB(driverId);
    }

    @Override
    public List<DriverInfo> getAllDrivers() {
        return driverInfoRepositoryPort.getAllDriversFromDB();
    }

    @Override
    public Optional<DriverInfo> getDriverByName(String driverName) {
        return driverInfoRepositoryPort.findByNameFromDB(driverName);
    }

    @Override
    public List<DriverInfo> getDriversByTeam(String team) {
        return driverInfoRepositoryPort.getDriversByTeamFromDB(team);
    }
}
