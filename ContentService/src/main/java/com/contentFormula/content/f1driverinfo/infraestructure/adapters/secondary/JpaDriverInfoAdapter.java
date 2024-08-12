package com.contentFormula.content.f1driverinfo.infraestructure.adapters.secondary;

import com.contentFormula.content.f1driverinfo.domain.model.DriverInfo;
import com.contentFormula.content.f1driverinfo.domain.model.Vehicle;
import com.contentFormula.content.f1driverinfo.domain.port.out.DriverInfoRepositoryPort;
import com.contentFormula.content.f1driverinfo.infraestructure.entities.DriverInfoEntity;
import com.contentFormula.content.f1driverinfo.infraestructure.entities.VehicleEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@AllArgsConstructor
@Component
public class JpaDriverInfoAdapter implements DriverInfoRepositoryPort {

    private final JpaDriverInfoRepository jpaDriverInfoRepository;
    private final JpaVehicleRepository jpaVehicleRepository;


    @Override
    public Optional<DriverInfo> saveDriverInfo(DriverInfo driverInfo) {
        DriverInfoEntity driverInfoEntity = DriverInfoMapper.toEntity(driverInfo);
        DriverInfoEntity savedDriverInfoEntity = jpaDriverInfoRepository.save(driverInfoEntity);
        return Optional.of(DriverInfoMapper.toDomain(savedDriverInfoEntity));
    }

    @Override
    public void updateDriverInfo(Long driverId, DriverInfo driverInfo) {
        Optional<DriverInfoEntity> existingDriverInfoOptional = jpaDriverInfoRepository.findById(driverId);
        if (existingDriverInfoOptional.isPresent()) {
            DriverInfoEntity driverInfoEntity = existingDriverInfoOptional.get();
            // Update existing DriverInfoEntity
            updateDriverInfoEntity(driverInfoEntity, driverInfo);
            // Save the updated DriverInfoEntity
            DriverInfoEntity updatedDriverInfoEntity = jpaDriverInfoRepository.save(driverInfoEntity);

        }
    }


    private void updateDriverInfoEntity(DriverInfoEntity existingEntity, DriverInfo newInfo) {
        DriverInfoEntity newDriver = DriverInfoMapper.toEntity(newInfo);
        existingEntity.setFullName(newDriver.getFullName());
        existingEntity.setDisplayName(newDriver.getDisplayName());
        existingEntity.setDateOfBirth(newDriver.getDateOfBirth());
        existingEntity.setShortName(newDriver.getShortName());
        existingEntity.setHeadshot(newDriver.getHeadshot());
        existingEntity.setFlag( newDriver.getFlag());
        existingEntity.setLinked(newDriver.isLinked());
        existingEntity.setActive(newDriver.isActive());
        existingEntity.setStatus(newDriver.getStatus());

        // Update Vehicle entities
        updateVehicles(existingEntity, newInfo.getVehicles());
    }


    private void updateVehicles(DriverInfoEntity driverInfoEntity, List<Vehicle> vehicles) {
        List<VehicleEntity> updatedVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            VehicleEntity vehicleEntity = findOrCreateVehicleEntity(vehicle, driverInfoEntity);
            updatedVehicles.add(vehicleEntity);
        }
        driverInfoEntity.setVehicles(updatedVehicles);
    }

    private VehicleEntity findOrCreateVehicleEntity(Vehicle vehicle, DriverInfoEntity driverInfoEntity) {
        Optional<VehicleEntity> vehicleEntityOptional = jpaVehicleRepository.findByChassis(vehicle.getChassis());

        VehicleEntity vehicleEntity;
        if (vehicleEntityOptional.isPresent()) {
            // Update the existing VehicleEntity
            vehicleEntity = vehicleEntityOptional.get();
            vehicleEntity.setManufacturer(vehicle.getManufacturer());
            vehicleEntity.setEngine(vehicle.getEngine());
            vehicleEntity.setTire(vehicle.getTire());
            vehicleEntity.setTeam(vehicle.getTeam());
        } else {
            // Create a new VehicleEntity
            vehicleEntity = new VehicleEntity();
            vehicleEntity.setManufacturer(vehicle.getManufacturer());
            vehicleEntity.setChassis(vehicle.getChassis());
            vehicleEntity.setEngine(vehicle.getEngine());
            vehicleEntity.setTire(vehicle.getTire());
            vehicleEntity.setTeam(vehicle.getTeam());
            vehicleEntity.setDriverInfo(driverInfoEntity);
        }
        return vehicleEntity;
    }

    @Override
    public List<Long> getAllDriverIds() {
        return jpaDriverInfoRepository.findAllDriverIds();
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
    public Optional<DriverInfo> getByName(String driverName) {
        return jpaDriverInfoRepository.findByFullName(driverName)
                .map(DriverInfoMapper::toDomain);
    }

}