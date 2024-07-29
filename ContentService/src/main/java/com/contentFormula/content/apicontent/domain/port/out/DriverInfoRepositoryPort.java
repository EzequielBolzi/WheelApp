package com.contentFormula.content.apicontent.domain.port.out;

import com.contentFormula.content.apicontent.domain.dtos.DriverInfoDto;
import com.contentFormula.content.apicontent.domain.model.DriverInfo;

import java.util.List;
import java.util.Optional;

public interface DriverInfoRepositoryPort {
    Optional<DriverInfo> updateDriverInfoInDB(Long driverId, DriverInfo driverInfo);
    Optional<DriverInfo> saveDriverInfoInDB(DriverInfo driverInfo);
    List<DriverInfo> getAllDriversFromDB();
    Optional<DriverInfo> getDriverInfoFromDB(Long driverId);
    List<DriverInfo> getDriversByTeamFromDB(String team);
    Optional<DriverInfo> findByNameFromDB(String driverName);
}
