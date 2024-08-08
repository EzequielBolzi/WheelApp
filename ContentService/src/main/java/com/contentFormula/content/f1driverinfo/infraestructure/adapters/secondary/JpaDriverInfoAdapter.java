package com.contentFormula.content.f1driverinfo.infraestructure.adapters.secondary;

import com.contentFormula.content.f1driverinfo.domain.model.DriverInfo;
import com.contentFormula.content.f1driverinfo.domain.port.out.DriverInfoRepositoryPort;
import com.contentFormula.content.f1driverinfo.infraestructure.entities.DriverInfoEntity;
import com.contentFormula.content.f1driverinfo.infraestructure.entities.VehicleEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@AllArgsConstructor
@Component
public class JpaDriverInfoAdapter implements DriverInfoRepositoryPort {

    private final JpaDriverInfoRepository jpaDriverInfoRepository;
    private final JpaVehicleRepository jpaVehicleRepository;


    @Override
    public Optional<DriverInfo> saveDriverInfoInDB(DriverInfo driverInfo) {
        DriverInfoEntity driverInfoEntity = DriverInfoMapper.toEntity(driverInfo);
        DriverInfoEntity savedDriverInfoEntity = jpaDriverInfoRepository.save(driverInfoEntity);
        return Optional.of(DriverInfoMapper.toDomain(savedDriverInfoEntity));
    }

    @Override
    public List<DriverInfo> getAllDrivers() {
        return jpaDriverInfoRepository.findAll().stream()
                .map(DriverInfoMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DriverInfo> getDriverInfo(Long driverId) {
        return jpaDriverInfoRepository.findById(driverId)
                .map(DriverInfoMapper::toDomain);
    }

    @Override
    public List<DriverInfo> getDriversByTeam(String team) {
        List<VehicleEntity> vehicles = jpaVehicleRepository.findByTeam(team);
        return vehicles.stream()
                .map(VehicleEntity::getDriverInfo)
                .distinct()
                .map(DriverInfoMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DriverInfo> findByName(String driverName) {
        return jpaDriverInfoRepository.findByFullName(driverName)
                .map(DriverInfoMapper::toDomain);
    }
}