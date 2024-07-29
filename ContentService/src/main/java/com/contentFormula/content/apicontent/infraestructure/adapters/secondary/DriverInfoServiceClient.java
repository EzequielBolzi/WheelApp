package com.contentFormula.content.apicontent.infraestructure.adapters.secondary;

import com.contentFormula.content.apicontent.domain.model.DriverInfo;
import com.contentFormula.content.apicontent.domain.model.Vehicle;
import com.contentFormula.content.apicontent.infraestructure.customexceptions.CustomUpdatedDriverSuccesfullyException;
import com.contentFormula.content.apicontent.infraestructure.customexceptions.CustomVehicleAlreadyException;
import com.contentFormula.content.apicontent.infraestructure.entities.DriverInfoEntity;
import com.contentFormula.content.apicontent.infraestructure.entities.VehicleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DriverInfoServiceClient {

    private final JpaDriverInfoRepository jpaDriverInfoRepository;
    private final JpaVehicleRepository jpaVehicleRepository;
    private final HttpClient httpClient;

    // Extract Information about Driver from EXTERNAL API
    public void fetchAndSaveDriverInfo(Long driverId) {
        Optional<DriverInfoEntity> existingDriverInfoOptional = jpaDriverInfoRepository.findById(driverId);

        // Fetch updated driver info from external API
        Optional<DriverInfo> driverInfoOptional = fetchDriverInfo(driverId);
        if (driverInfoOptional.isPresent()) {
            DriverInfo driverInfo = driverInfoOptional.get();
            DriverInfoEntity driverInfoEntity;
            if (existingDriverInfoOptional.isPresent()) {
                driverInfoEntity = existingDriverInfoOptional.get();
                updateDriverInfoEntity(driverInfoEntity, driverInfo);
                throw new CustomUpdatedDriverSuccesfullyException("Driver information updated.");
            } else {
                driverInfoEntity = DriverInfoMapper.toEntity(driverInfo);
            }

            jpaDriverInfoRepository.save(driverInfoEntity);
        } else {
            throw new RuntimeException("Driver not found");
        }
    }

    private void updateDriverInfoEntity(DriverInfoEntity existingEntity, DriverInfo newInfo) {
        existingEntity.setFullName(newInfo.getFullName());
        existingEntity.setDisplayName(newInfo.getDisplayName());
        existingEntity.setDateOfBirth(newInfo.getDateOfBirth());
        existingEntity.setShortName(newInfo.getShortName());
        existingEntity.setHeadshot(newInfo.getHeadshot());
        existingEntity.setFlag(DriverInfoMapper.toEntity(newInfo).getFlag());
        existingEntity.setLinked(newInfo.isLinked());
        existingEntity.setActive(newInfo.isActive());
        existingEntity.setStatus(DriverInfoMapper.toEntity(newInfo).getStatus());

        // Update vehicles
        List<VehicleEntity> updatedVehicles = new ArrayList<>();
        for (Vehicle vehicle : newInfo.getVehicles()) {
            VehicleEntity vehicleEntity = findOrCreateVehicleEntity(vehicle, newInfo);
            updatedVehicles.add(vehicleEntity);
        }
        existingEntity.setVehicles(updatedVehicles);
    }

    private VehicleEntity findOrCreateVehicleEntity(Vehicle vehicleNew, DriverInfo driverInfoNew) {
        Optional<VehicleEntity> verificationVehicleExists = jpaVehicleRepository.findByChassis(vehicleNew.getChassis());

        if (!verificationVehicleExists.isPresent()) {
            VehicleEntity vehicleEntity = new VehicleEntity();
            vehicleEntity.setManufacturer(vehicleNew.getManufacturer());
            vehicleEntity.setChassis(vehicleNew.getChassis());
            vehicleEntity.setEngine(vehicleNew.getEngine());
            vehicleEntity.setTire(vehicleNew.getTire());
            vehicleEntity.setTeam(vehicleNew.getTeam());
            vehicleEntity.setDriverInfo(DriverInfoMapper.toEntity(driverInfoNew));
            return vehicleEntity;
        }
        throw new CustomVehicleAlreadyException("Vehicle already exists.");
    }

    private Optional<DriverInfo> fetchDriverInfo(Long driverId) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://f1-motorsport-data.p.rapidapi.com/athlete-info?athleteId=" + driverId))
                    .header("x-rapidapi-key", "a3344a18efmsh1df8778ab3f8611p13f0d9jsn9d5e72743ec2")
                    .header("x-rapidapi-host", "f1-motorsport-data.p.rapidapi.com")
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            DriverInfo driverInfo = DriverInfoMapper.fromJson(response.body());
            return Optional.of(driverInfo);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}