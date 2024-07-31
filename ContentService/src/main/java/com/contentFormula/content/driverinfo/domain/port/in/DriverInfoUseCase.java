package com.contentFormula.content.driverinfo.domain.port.in;

import com.contentFormula.content.driverinfo.domain.model.DriverInfo;

import java.util.List;
import java.util.Optional;

public interface DriverInfoUseCase {
    Optional<DriverInfo> createDriverInfo (DriverInfo driverInfo);
    Optional<DriverInfo> getDriverInfo(Long driverId);
    List<DriverInfo> getAllDrivers();
    Optional<DriverInfo> getDriverByName(String driverName);
    List<DriverInfo> getDriversPerTeam(String team);
}
