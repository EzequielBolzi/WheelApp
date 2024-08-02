package com.contentFormula.content.driverstatistics.infraestructure.adapters.secondary;

import com.contentFormula.content.driverinfo.domain.model.DriverInfo;
import com.contentFormula.content.driverinfo.domain.model.Vehicle;
import com.contentFormula.content.driverstatistics.domain.dtos.DriverSeasonStatsDto;
import com.contentFormula.content.driverstatistics.domain.model.DriverSeasonStats;
import com.contentFormula.content.driverstatistics.infraestructure.entities.DriverSeasonStatsEntity;
import com.contentFormula.content.driverinfo.infraestructure.adapters.secondary.DriverInfoMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DriverSeasonStatsMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static DriverSeasonStats toDomain(DriverSeasonStatsEntity entity) {
        if (entity == null) return null;
        DriverInfo driverInfo = driverInfoToDomain(entity.getDriverInfo());
        return new DriverSeasonStats(
                entity.getId(),
                entity.getYear(),
                entity.getRank(),
                entity.getStarts(),
                entity.getWins(),
                entity.getPoles(),
                entity.getTop5(),
                entity.getTop10(),
                entity.getPoints(),
                entity.getAvgStart(),
                entity.getAvgFinish(),
                driverInfo
        );
    }

    public static DriverSeasonStatsEntity toEntity(DriverSeasonStats domain) {
        if (domain == null) return null;
        DriverSeasonStatsEntity entity = new DriverSeasonStatsEntity();
        entity.setId(domain.getId());
        entity.setYear(domain.getYear());
        entity.setRank(domain.getRank());
        entity.setStarts(domain.getStarts());
        entity.setWins(domain.getWins());
        entity.setPoles(domain.getPoles());
        entity.setTop5(domain.getTop5());
        entity.setTop10(domain.getTop10());
        entity.setPoints(domain.getPoints());
        entity.setAvgStart(domain.getAvgStart());
        entity.setAvgFinish(domain.getAvgFinish());
        entity.setDriverInfo(DriverInfoMapper.toEntity(domain.getDriverInfo()));
        return entity;
    }

    public static DriverSeasonStatsDto toDto(DriverSeasonStats domain) {
        if (domain == null) return null;
        DriverSeasonStatsDto dto = new DriverSeasonStatsDto();
        dto.setYear(domain.getYear());
        dto.setRank(domain.getRank());
        dto.setStarts(domain.getStarts());
        dto.setWins(domain.getWins());
        dto.setPoles(domain.getPoles());
        dto.setTop5(domain.getTop5());
        dto.setTop10(domain.getTop10());
        dto.setPoints(domain.getPoints());
        dto.setAvgStart(domain.getAvgStart());
        dto.setAvgFinish(domain.getAvgFinish());
        dto.setNameDriver(domain.getDriverInfo().getFullName());
        return dto;
    }

    public static List<DriverSeasonStatsDto> toDto(List<DriverSeasonStats> domains) {
        return domains.stream().map(DriverSeasonStatsMapper::toDto).collect(Collectors.toList());
    }

    public static List<DriverSeasonStats> fromJson(String jsonString) throws Exception {
        JsonNode root = objectMapper.readTree(jsonString);
        List<DriverSeasonStats> stats = new ArrayList<>();
        if (root.isArray()) {
            for (JsonNode node : root) {
                DriverSeasonStats stat = new DriverSeasonStats();
                stat.setYear(node.path("year").asInt());
                stat.setRank(node.path("rank").asInt());
                stat.setStarts(node.path("starts").asInt());
                stat.setWins(node.path("wins").asInt());
                stat.setPoles(node.path("poles").asInt());
                stat.setTop5(node.path("top5").asInt());
                stat.setTop10(node.path("top10").asInt());
                stat.setPoints(node.path("points").asInt());
                stat.setAvgStart(node.path("avgStart").asText());
                stat.setAvgFinish(node.path("avgFinish").asText());
                stats.add(stat);
            }
        }
        return stats;
    }

    private static DriverInfo driverInfoToDomain(com.contentFormula.content.driverinfo.infraestructure.entities.DriverInfoEntity entity) {
        if (entity == null) return null;
        return DriverInfoMapper.toDomain(entity);
    }


}