package com.contentFormula.content.f1schedule.infraestructure.adapters.secondary;

import com.contentFormula.content.f1driverRaceresult.domain.model.DriverRaceResult;
import com.contentFormula.content.f1racereport.domain.model.F1RaceReport;
import com.contentFormula.content.f1schedule.domain.model.F1Schedule;
import com.contentFormula.content.f1schedule.infraestructure.entities.F1ScheduleEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class F1ScheduleMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static F1Schedule toDomain(F1ScheduleEntity entity) {
        if (entity == null) {
            return null;
        }

        F1Schedule domain = new F1Schedule();
        domain.setStartDate(entity.getStartDate());
        domain.setEndDate(entity.getEndDate());
        domain.setCompleted(entity.isCompleted());
        domain.setGrandPrix(entity.getGrandPrix());
        domain.setCircuit(entity.getCircuit());
        domain.setPostponedOrCanceled(entity.isPostponedOrCanceled());
        domain.setWinner(entity.getWinner());

        return domain;
    }

    public static F1ScheduleEntity toEntity(F1Schedule domain) {
        if (domain == null) {
            return null;
        }

        F1ScheduleEntity entity = new F1ScheduleEntity();
        entity.setStartDate(domain.getStartDate());
        entity.setEndDate(domain.getEndDate());
        entity.setCompleted(domain.isCompleted());
        entity.setGrandPrix(domain.getGrandPrix());
        entity.setCircuit(domain.getCircuit());
        entity.setPostponedOrCanceled(domain.isPostponedOrCanceled());
        entity.setWinner(domain.getWinner());

        return entity;
    }

    public static List<F1Schedule> fromJson(String jsonString) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(jsonString);

        List<F1Schedule> schedules = new ArrayList<>();

        // Iterate over each date and its associated races
        for (Iterator<Map.Entry<String, JsonNode>> it = root.fields(); it.hasNext(); ) {
            Map.Entry<String, JsonNode> entry = it.next();
            String dateKey = entry.getKey(); // Not used, just iterating over races
            JsonNode racesNode = entry.getValue();

            // Iterate over each race in the array
            for (JsonNode raceNode : racesNode) {
                String startDateStr = raceNode.path("startDate").asText();
                String endDateStr = raceNode.path("endDate").asText();

                ZonedDateTime startDate = parseDate(startDateStr);
                ZonedDateTime endDate = parseDate(endDateStr);

                if (startDate == null || endDate == null) {
                    System.err.println("Skipping schedule due to invalid dates: startDate=" + startDateStr + ", endDate=" + endDateStr);
                    continue;  // Skip this entry if dates are invalid
                }

                F1Schedule schedule = new F1Schedule();
                schedule.setStartDate(startDate);
                schedule.setEndDate(endDate);
                schedule.setCompleted(raceNode.path("completed").asBoolean());
                schedule.setGrandPrix(raceNode.path("gPrx").asText());
                schedule.setCircuit(raceNode.path("crct").asText());
                schedule.setPostponedOrCanceled(raceNode.path("isPostponedOrCanceled").asBoolean());
                schedule.setWinner(raceNode.path("winner").asText());
                schedules.add(schedule);
            }
        }
        return schedules;
    }

    private static ZonedDateTime parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME.withZone(ZoneOffset.UTC);
        return ZonedDateTime.parse(dateString, formatter);
    }
}