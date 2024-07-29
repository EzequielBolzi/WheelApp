package com.contentFormula.content.apicontent.infraestructure.adapters.secondary;

import com.contentFormula.content.apicontent.domain.dtos.DriverInfoDto;
import com.contentFormula.content.apicontent.domain.model.DriverInfo;
import com.contentFormula.content.apicontent.domain.model.Vehicle;
import com.contentFormula.content.apicontent.infraestructure.entities.DriverInfoEntity;
import com.contentFormula.content.apicontent.infraestructure.entities.VehicleEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DriverInfoMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static DriverInfo toDomain(DriverInfoEntity entity) {
        if (entity == null) return null;

        List<Vehicle> vehicleDomainList = new ArrayList<>();
        if (entity.getVehicles() != null) {
            for (VehicleEntity vehicleEntity : entity.getVehicles()) {
                vehicleDomainList.add(new Vehicle(
                        vehicleEntity.getNumber(),
                        vehicleEntity.getManufacturer(),
                        vehicleEntity.getChassis(),
                        vehicleEntity.getEngine(),
                        vehicleEntity.getTire(),
                        vehicleEntity.getTeam(),
                        null
                ));
            }
        }

        return new DriverInfo(
                entity.getDriverIdentifier(),
                entity.getDriverId(),
                entity.getFullName(),
                entity.getDisplayName(),
                entity.getDateOfBirth(),
                entity.getShortName(),
                entity.getHeadshot(),
                vehicleDomainList,
                flagToDomain(entity.getFlag()),
                entity.isLinked(),
                entity.isActive(),
                statusToDomain(entity.getStatus())
        );
    }

    public static DriverInfoEntity toEntity(DriverInfo domain) {
        if (domain == null) return null;

        DriverInfoEntity entity = new DriverInfoEntity();
        entity.setDriverIdentifier(domain.getId());
        entity.setDriverId(domain.getDriverId());
        entity.setFullName(domain.getFullName());
        entity.setDisplayName(domain.getDisplayName());
        entity.setDateOfBirth(domain.getDateOfBirth());
        entity.setShortName(domain.getShortName());
        entity.setHeadshot(domain.getHeadshot());

        if (domain.getVehicles() != null) {
            List<VehicleEntity> vehicleEntityList = new ArrayList<>();
            for (Vehicle vehicle : domain.getVehicles()) {
                VehicleEntity vehicleEntity = new VehicleEntity();
                vehicleEntity.setNumber(vehicle.getNumber());
                vehicleEntity.setManufacturer(vehicle.getManufacturer());
                vehicleEntity.setChassis(vehicle.getChassis());
                vehicleEntity.setEngine(vehicle.getEngine());
                vehicleEntity.setTire(vehicle.getTire());
                vehicleEntity.setTeam(vehicle.getTeam());
                vehicleEntity.setDriverInfo(entity);
                vehicleEntityList.add(vehicleEntity);
            }
            entity.setVehicles(vehicleEntityList);
        }
        entity.setFlag(flagToEntity(domain.getFlag()));
        entity.setLinked(domain.isLinked());
        entity.setActive(domain.isActive());
        entity.setStatus(statusToEntity(domain.getStatus()));
        return entity;
    }

    public static DriverInfoDto toDto(DriverInfo domain) {
        if (domain == null) return null;

        List<Vehicle> vehicles = domain.getVehicles() != null ? domain.getVehicles() : new ArrayList<>();

        int age = calculateAge(domain.getDateOfBirth());

        String team = vehicles.isEmpty() ? null : vehicles.get(vehicles.size() - 1).getTeam();

        return new DriverInfoDto(
                domain.getFullName(),
                age,
                domain.getHeadshot(),
                team,
                domain.getFlag().getAlt()
        );
    }

    public static List<DriverInfoDto> toDto(List<DriverInfo> domains) {
        return domains.stream().map(DriverInfoMapper::toDto).collect(Collectors.toList());
    }

    public static DriverInfo fromJson(String jsonString) throws Exception {
        JsonNode root = objectMapper.readTree(jsonString);
        DriverInfo driverInfo = new DriverInfo();
        driverInfo.setDriverId(root.get("id").asLong());
        driverInfo.setFullName(root.get("fullName").asText());
        driverInfo.setDisplayName(root.get("displayName").asText());
        driverInfo.setDateOfBirth(ZonedDateTime.parse(root.get("dateOfBirth").asText()));
        driverInfo.setShortName(root.get("shortName").asText());
        driverInfo.setHeadshot(root.get("headshot").asText());

        // Mapping single vehicle from JSON
        List<Vehicle> vehicles = new ArrayList<>();
        JsonNode vehiclesNode = root.get("vehicles");
        if (vehiclesNode.isArray()) {
            for (JsonNode vehicleNode : vehiclesNode) {
                Vehicle vehicle = new Vehicle(
                        vehicleNode.get("number").asText(),
                        vehicleNode.get("manufacturer").asText(),
                        vehicleNode.get("chassis").asText(),
                        vehicleNode.get("engine").asText(),
                        vehicleNode.get("tire").asText(),
                        vehicleNode.get("team").asText(),
                        driverInfo
                );
                vehicles.add(vehicle);
            }
        }
        driverInfo.setVehicles(vehicles);

        // Mapping flag from JSON
        JsonNode flagNode = root.get("flag");
        DriverInfo.Flag flag = new DriverInfo.Flag(
                flagNode.get("href").asText(),
                flagNode.get("alt").asText(),
                List.of(flagNode.get("rel").get(0).asText()));
        driverInfo.setFlag(flag);

        driverInfo.setLinked(root.get("linked").asBoolean());
        driverInfo.setActive(root.get("active").asBoolean());

        // Mapping status from JSON
        JsonNode statusNode = root.get("status");
        DriverInfo.Status status = new DriverInfo.Status(
                statusNode.get("id").asText(),
                statusNode.get("name").asText(),
                statusNode.get("type").asText(),
                statusNode.get("abbreviation").asText());
        driverInfo.setStatus(status);

        return driverInfo;
    }

    private static int calculateAge(ZonedDateTime dateOfBirth) {
        if (dateOfBirth == null) return 0;
        LocalDate birthDate = dateOfBirth.toLocalDate();
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }

    private static DriverInfo.Flag flagToDomain(DriverInfoEntity.Flag entity) {
        if (entity == null) return null;
        return new DriverInfo.Flag(entity.getHref(), entity.getAlt(), entity.getRel());
    }

    private static DriverInfoEntity.Flag flagToEntity(DriverInfo.Flag domain) {
        if (domain == null) return null;
        return new DriverInfoEntity.Flag(domain.getHref(), domain.getAlt(), domain.getRel());
    }

    private static DriverInfo.Status statusToDomain(DriverInfoEntity.Status entity) {
        if (entity == null) return null;
        return new DriverInfo.Status(entity.getId(), entity.getName(), entity.getType(), entity.getAbbreviation());
    }

    private static DriverInfoEntity.Status statusToEntity(DriverInfo.Status domain) {
        if (domain == null) return null;
        return new DriverInfoEntity.Status(domain.getId(), domain.getName(), domain.getType(), domain.getAbbreviation());
    }
}