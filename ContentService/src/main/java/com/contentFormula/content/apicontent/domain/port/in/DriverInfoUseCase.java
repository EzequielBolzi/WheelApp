package com.contentFormula.content.apicontent.domain.port.in;

import com.contentFormula.content.apicontent.domain.dtos.DriverInfoDto;
import com.contentFormula.content.apicontent.domain.model.DriverInfo;

import java.util.List;
import java.util.Optional;

public interface DriverInfoUseCase {
    Optional<DriverInfo> createDriverInfo (DriverInfo driverInfo);
    Optional<DriverInfo> updateDriver(Long driverId, DriverInfo driverInfo);
    Optional<DriverInfo> getDriverInfo(Long driverId);
    List<DriverInfo> getAllDrivers();
    Optional<DriverInfo> getDriverByName(String driverName);
    List<DriverInfo> getDriversByTeam(String team);
}
