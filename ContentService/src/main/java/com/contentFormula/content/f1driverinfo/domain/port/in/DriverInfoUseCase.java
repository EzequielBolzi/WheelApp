package com.contentFormula.content.f1driverinfo.domain.port.in;

import com.contentFormula.content.f1driverinfo.domain.model.DriverInfo;

import java.util.List;
import java.util.Optional;

public interface DriverInfoUseCase {
    Optional<DriverInfo> createDriverInfo (DriverInfo driverInfo);
    Optional<DriverInfo> getDriverInfo(Long driverId);
    List<DriverInfo> getAllDrivers();
    Optional<DriverInfo> getDriverByName(String driverName);
    List<DriverInfo> getDriversPerTeam(String team);
    List<Long> getAllDriverIds();
    void updateDriverInfoInDB(Long driverId, DriverInfo driverInfo);


}

