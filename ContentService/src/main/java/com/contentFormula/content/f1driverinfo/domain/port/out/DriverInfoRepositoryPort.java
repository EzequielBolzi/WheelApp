package com.contentFormula.content.f1driverinfo.domain.port.out;

import com.contentFormula.content.f1driverinfo.domain.model.DriverInfo;

import java.util.List;
import java.util.Optional;

public interface DriverInfoRepositoryPort {
    Optional<DriverInfo> saveDriverInfoInDB(DriverInfo driverInfo);
    List<DriverInfo> getAllDrivers();
    Optional<DriverInfo> getDriverInfo(Long driverId);
    List<DriverInfo> getDriversByTeam(String team);
    Optional<DriverInfo> findByName(String driverName);
}
