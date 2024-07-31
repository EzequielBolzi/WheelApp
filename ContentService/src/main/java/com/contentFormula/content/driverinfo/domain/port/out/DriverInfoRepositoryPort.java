package com.contentFormula.content.driverinfo.domain.port.out;

import com.contentFormula.content.driverinfo.domain.model.DriverInfo;

import java.util.List;
import java.util.Optional;

public interface DriverInfoRepositoryPort {
    Optional<DriverInfo> saveDriverInfoInDB(DriverInfo driverInfo);
    List<DriverInfo> getAllDriversFromDB();
    Optional<DriverInfo> getDriverInfoFromDB(Long driverId);
    List<DriverInfo> getDriversByTeamFromDB(String team);
    Optional<DriverInfo> findByNameFromDB(String driverName);
}
