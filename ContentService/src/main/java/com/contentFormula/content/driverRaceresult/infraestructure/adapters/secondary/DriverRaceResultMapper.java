package com.contentFormula.content.driverRaceresult.infraestructure.adapters.secondary;

import com.contentFormula.content.driverRaceresult.domain.dtos.RaceResultDTO;
import com.contentFormula.content.driverRaceresult.domain.model.DriverRaceResult;
import com.contentFormula.content.driverRaceresult.infraestructure.entities.DriverRaceResultEntity;

import com.contentFormula.content.driverinfo.domain.dtos.DriverInfoDto;
import com.contentFormula.content.driverinfo.domain.model.DriverInfo;
import com.contentFormula.content.driverinfo.infraestructure.adapters.secondary.DriverInfoMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class DriverRaceResultMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static DriverRaceResult toDomain(DriverRaceResultEntity entity) {
        if (entity == null) return null;

        return new DriverRaceResult(
                entity.getId(),
                entity.getDate(),
                entity.getRace(),
                entity.getPlace(),
                entity.getStartPosition(),
                entity.getLaps(),
                entity.getPoints(),
                DriverInfoMapper.toDomain(entity.getDriverInfo())
        );
    }

    public static DriverRaceResultEntity toEntity(DriverRaceResult domain) {
        if (domain == null) return null;

        DriverRaceResultEntity entity = new DriverRaceResultEntity();
        entity.setId(domain.getId());
        entity.setDate(domain.getDate());
        entity.setRace(domain.getRace());
        entity.setPlace(domain.getPlace());
        entity.setStartPosition(domain.getStartPosition());
        entity.setLaps(domain.getLaps());
        entity.setPoints(domain.getPoints());
        entity.setDriverInfo(DriverInfoMapper.toEntity(domain.getDriverInfo()));

        return entity;


    }
    public static RaceResultDTO toDto(DriverRaceResult domain) {
        if (domain == null) return null;

        RaceResultDTO dto = new RaceResultDTO();
        dto.setDate(domain.getDate());
        dto.setRace(domain.getRace());
        dto.setPlace(domain.getPlace());
        dto.setStart(domain.getStartPosition());
        dto.setLaps(domain.getLaps());
        dto.setPoints(domain.getPoints());
        return dto;
    }
    public static List<RaceResultDTO> toDto(List<DriverRaceResult> domains) {
        return domains.stream().map(DriverRaceResultMapper::toDto).collect(Collectors.toList());
    }

    public static List<DriverRaceResult> fromJson(String jsonString) throws Exception {
        JsonNode root = objectMapper.readTree(jsonString);
        List<DriverRaceResult> results = new ArrayList<>();

        if (root.isArray()) {
            for (JsonNode node : root) {
                DriverRaceResult result = new DriverRaceResult();
                result.setDate(node.path("date").asText(null));
                result.setRace(node.path("race").asText(null));
                result.setPlace(node.path("place").asInt(0));
                result.setStartPosition(node.path("start").asInt(0));
                result.setLaps(node.path("laps").asInt(0));
                result.setPoints(node.path("points").asInt(0));
                results.add(result);
            }
        }

        return results;
    }
    }
