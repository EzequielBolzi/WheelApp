package com.contentFormula.content.f1driverinfo.infraestructure.adapters.secondary;

import com.contentFormula.content.f1driverinfo.domain.dtos.DriverInfoDto;
import com.contentFormula.content.f1driverinfo.domain.model.DriverInfo;
import com.contentFormula.content.f1driverinfo.domain.model.Vehicle;
import com.contentFormula.content.f1driverinfo.infraestructure.entities.DriverInfoEntity;
import com.contentFormula.content.f1driverinfo.infraestructure.entities.VehicleEntity;
import com.contentFormula.content.f1racereport.infraestructure.adapters.secondary.F1RaceReportAdapter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DriverInfoMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(DriverInfoMapper.class);

    /**
     * Converts a DriverInfoEntity to a DriverInfo domain model.
     *
     * @param entity the DriverInfoEntity to convert
     * @return the corresponding DriverInfo domain model
     */

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
    /**
     * Converts a DriverInfo domain model to a DriverInfoEntity.
     *
     * @param domain the DriverInfo domain model to convert
     * @return the corresponding DriverInfoEntity
     */

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

    /**
     * Converts a DriverInfo domain model to a DriverInfoDto.
     *
     * @param domain the DriverInfo domain model to convert
     * @return the corresponding DriverInfoDto
     */

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
    /**
     * Converts a list of DriverInfo domain models to a list of DriverInfoDto.
     *
     * @param domains the list of DriverInfo domain models to convert
     * @return the corresponding list of DriverInfoDto
     */

    public static List<DriverInfoDto> toDto(List<DriverInfo> domains) {
        return domains.stream().map(DriverInfoMapper::toDto).collect(Collectors.toList());
    }

    public static DriverInfo fromJson(String jsonString) throws Exception {
        JsonNode root = objectMapper.readTree(jsonString);

        DriverInfo driverInfo = new DriverInfo();
        driverInfo.setDriverId(getLongSafely(root, "id"));
        driverInfo.setFullName(getTextSafely(root, "fullName"));
        driverInfo.setDisplayName(getTextSafely(root, "displayName"));
        driverInfo.setDateOfBirth(getDateTimeSafely(root, "dateOfBirth"));
        driverInfo.setShortName(getTextSafely(root, "shortName"));
        driverInfo.setHeadshot(getTextSafely(root, "headshot"));

        // Mapping vehicles from JSON
        JsonNode vehiclesNode = root.get("vehicles");
        if (vehiclesNode != null && vehiclesNode.isArray()) {
            List<Vehicle> vehicles = new ArrayList<>();
            for (JsonNode vehicleNode : vehiclesNode) {
                Vehicle vehicle = new Vehicle();
                vehicle.setNumber(getTextSafely(vehicleNode, "number"));
                vehicle.setManufacturer(getTextSafely(vehicleNode, "manufacturer"));
                vehicle.setChassis(getTextSafely(vehicleNode, "chassis"));
                vehicle.setEngine(getTextSafely(vehicleNode, "engine"));
                vehicle.setTire(getTextSafely(vehicleNode, "tire"));
                vehicle.setTeam(getTextSafely(vehicleNode, "team"));
                vehicles.add(vehicle);
            }
            driverInfo.setVehicles(vehicles);
        }

        // Mapping flag from JSON
        JsonNode flagNode = root.get("flag");
        if (flagNode != null && flagNode.isObject()) {
            DriverInfo.Flag flag = new DriverInfo.Flag(
                    getTextSafely(flagNode, "href"),
                    getTextSafely(flagNode, "alt"),
                    getListSafely(flagNode, "rel")
            );
            driverInfo.setFlag(flag);
        }

        driverInfo.setLinked(getBooleanSafely(root, "linked", false));
        driverInfo.setActive(getBooleanSafely(root, "active", false));

        // Mapping status from JSON
        JsonNode statusNode = root.get("status");
        if (statusNode != null && statusNode.isObject()) {
            DriverInfo.Status status = new DriverInfo.Status(
                    getTextSafely(statusNode, "id"),
                    getTextSafely(statusNode, "name"),
                    getTextSafely(statusNode, "type"),
                    getTextSafely(statusNode, "abbreviation")
            );
            driverInfo.setStatus(status);
        }

        return driverInfo;
    }

    private static String getTextSafely(JsonNode node, String fieldName) {
        JsonNode fieldNode = node.get(fieldName);
        return (fieldNode != null && !fieldNode.isNull()) ? fieldNode.asText() : null;
    }

    private static boolean getBooleanSafely(JsonNode node, String fieldName, boolean defaultValue) {
        JsonNode fieldNode = node.get(fieldName);
        return (fieldNode != null && !fieldNode.isNull()) ? fieldNode.asBoolean() : defaultValue;
    }

    private static long getLongSafely(JsonNode node, String fieldName) {
        JsonNode fieldNode = node.get(fieldName);
        return (fieldNode != null && !fieldNode.isNull()) ? fieldNode.asLong() : 0;
    }

    private static ZonedDateTime getDateTimeSafely(JsonNode node, String fieldName) {
        JsonNode fieldNode = node.get(fieldName);
        if (fieldNode == null || fieldNode.isNull() || fieldNode.asText().isEmpty()) {
            return null; // or return a default value, or throw an exception
        }
        try {
            return ZonedDateTime.parse(fieldNode.asText());
        } catch (DateTimeParseException e) {
            // log the error
            logger.error("Error parsing date-time value: {}", e.getMessage());
            return null; // or return a default value, or throw an exception
        }
    }

    private static List<String> getListSafely(JsonNode node, String fieldName) {
        JsonNode fieldNode = node.get(fieldName);
        if (fieldNode != null && fieldNode.isArray()) {
            List<String> result = new ArrayList<>();
            for (JsonNode item : fieldNode) {
                result.add(item.asText());
            }
            return result;
        }
        return Collections.emptyList();
    }
    /**
     * Calculates the age based on the date of birth.
     *
     * @param dateOfBirth the date of birth
     * @return the calculated age
     */
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