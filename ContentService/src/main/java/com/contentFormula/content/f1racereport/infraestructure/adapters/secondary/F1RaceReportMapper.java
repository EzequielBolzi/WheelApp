package com.contentFormula.content.f1racereport.infraestructure.adapters.secondary;

import com.contentFormula.content.f1racereport.domain.model.*;
import com.contentFormula.content.f1racereport.infraestructure.entities.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.ParseException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class F1RaceReportMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();


    public static F1RaceReport toDomain(F1RaceReportEntity entity) {
        if (entity == null) {
            return null;
        }

        F1RaceReport domain = new F1RaceReport();
        domain.setId(entity.getId());
        domain.setRaceName(entity.getRaceName());
        domain.setShortName(entity.getShortName());
        domain.setStartDate(entity.getStartDate());
        domain.setEndDate(entity.getEndDate());
        domain.setSeason(entity.getSeason());

        domain.setEventInfo(toDomain(entity.getEventInfo()));

        List<F1DriverPosition> positions = entity.getPositions().stream()
                .map(F1RaceReportMapper::toDomain)
                .collect(Collectors.toList());
        domain.setPositions(positions);

        return domain;
    }

    public static F1DriverPosition toDomain(F1DriverPositionEntity entity) {
        if (entity == null) {
            return null;
        }

        F1DriverPosition domain = new F1DriverPosition();
        domain.setOrder(entity.getOrder());
        domain.setWinner(entity.isWinner());
        domain.setType(entity.getType());
        domain.setRaceType(entity.getRaceType());
        domain.setDriverInfo(entity.getDriverInfo());
        domain.setStateInfo(toDomain(entity.getStateInfo()));
        domain.setTeamColor(entity.getTeamColor());
        domain.setTeam(entity.getTeam());
        domain.setDisplayName(entity.getDisplayName());

        return domain;
    }



    public static F1RaceReport.Image toDomain(F1RaceReportEntity.Image entity) {
        if (entity == null) {
            return null;
        }

        F1RaceReport.Image domain = new F1RaceReport.Image();
        domain.setHref(entity.getHref());
        domain.setAlt(entity.getAlt());

        return domain;
    }

    public static F1RaceReport.EventInfo toDomain(F1RaceReportEntity.EventInfo entity) {
        if (entity == null) {
            return null;
        }

        F1RaceReport.EventInfo domain = new F1RaceReport.EventInfo();
        domain.setVenue(toDomain(entity.getVenue()));

        return domain;
    }

    public static F1RaceReport.Venue toDomain(F1RaceReportEntity.Venue entity) {
        if (entity == null) {
            return null;
        }

        F1RaceReport.Venue domain = new F1RaceReport.Venue();
        domain.setId(entity.getId());
        domain.setFullName(entity.getFullName());
        domain.setAddress(toDomain(entity.getAddress()));
        domain.setLength(entity.getLength());
        domain.setCountryFlag(toDomain(entity.getCountryFlag()));
        domain.setCircuitDiagram(toDomain(entity.getCircuitDiagram()));

        return domain;
    }

    public static F1RaceReport.Address toDomain(F1RaceReportEntity.Address entity) {
        if (entity == null) {
            return null;
        }

        F1RaceReport.Address domain = new F1RaceReport.Address();
        domain.setCity(entity.getCity());
        domain.setCountry(entity.getCountry());

        return domain;
    }

    public static F1RaceReport.StateInfo toDomain(F1DriverPositionEntity.StateInfo entity) {
        if (entity == null) {
            return null;
        }

        F1RaceReport.StateInfo domain = new F1RaceReport.StateInfo();
        domain.setPeriod(entity.getPeriod());
        domain.setDisplayValue(entity.getDisplayValue());
        domain.setName(entity.getName());
        domain.setState(entity.getState());
        domain.setCompleted(entity.isCompleted());
        domain.setLapsCompleted(entity.getLapsCompleted());
        domain.setBehindTime(entity.getBehindTime());
        domain.setPitsTaken(entity.getPitsTaken());
        domain.setPlace(entity.getPlace());
        domain.setTotalTime(entity.getTotalTime());

        return domain;
    }

    public static F1RaceReportEntity toEntity(F1RaceReport domain) {
        if (domain == null) {
            return null;
        }

        F1RaceReportEntity entity = new F1RaceReportEntity();
        entity.setId(domain.getId());
        entity.setRaceName(domain.getRaceName());
        entity.setShortName(domain.getShortName());
        entity.setStartDate(domain.getStartDate());
        entity.setEndDate(domain.getEndDate());
        entity.setSeason(domain.getSeason());
        entity.setEventInfo(toEntity(domain.getEventInfo()));

        List<F1DriverPositionEntity> positions = domain.getPositions().stream()
                .map(position -> {
                    F1DriverPositionEntity positionEntity = toEntity(position);
                    positionEntity.setRaceReport(entity);
                    return positionEntity;
                })
                .collect(Collectors.toList());
        entity.setPositions(positions);

        return entity;
    }

    public static F1DriverPositionEntity toEntity(F1DriverPosition domain) {
        if (domain == null) {
            return null;
        }

        F1DriverPositionEntity entity = new F1DriverPositionEntity();
        entity.setOrder(domain.getOrder());
        entity.setWinner(domain.isWinner());
        entity.setType(domain.getType());
        entity.setRaceType(domain.getRaceType());
        entity.setDriverInfo(domain.getDriverInfo());
        entity.setStateInfo(toEntity(domain.getStateInfo()));
        entity.setTeam(domain.getTeam());
        entity.setTeamColor(domain.getTeamColor());
        entity.setDisplayName(domain.getDisplayName());

        return entity;
    }



    public static F1RaceReportEntity.Image toEntity(F1RaceReport.Image domain) {
        if (domain == null) {
            return null;
        }

        F1RaceReportEntity.Image entity = new F1RaceReportEntity.Image();
        entity.setHref(domain.getHref());
        entity.setAlt(domain.getAlt());

        return entity;
    }

    public static F1RaceReportEntity.EventInfo toEntity(F1RaceReport.EventInfo domain) {
        if (domain == null) {
            return null;
        }

        F1RaceReportEntity.EventInfo entity = new F1RaceReportEntity.EventInfo();
        entity.setVenue(toEntity(domain.getVenue()));

        return entity;
    }

    public static F1RaceReportEntity.Venue toEntity(F1RaceReport.Venue  domain) {
        if (domain == null) {
            return null;
        }

        F1RaceReportEntity.Venue  entity = new F1RaceReportEntity.Venue ();
        entity.setId(domain.getId());
        entity.setFullName(domain.getFullName());
        entity.setAddress(toEntity(domain.getAddress()));
        entity.setLength(domain.getLength());
        entity.setCountryFlag(toEntity(domain.getCountryFlag()));
        entity.setCircuitDiagram(toEntity(domain.getCircuitDiagram()));

        return entity;
    }

    public static F1RaceReportEntity.Address toEntity(F1RaceReport.Address domain) {
        if (domain == null) {
            return null;
        }

        F1RaceReportEntity.Address entity = new F1RaceReportEntity.Address();
        entity.setCity(domain.getCity());
        entity.setCountry(domain.getCountry());

        return entity;
    }

    public static F1DriverPositionEntity.StateInfo  toEntity(F1RaceReport.StateInfo domain) {
        if (domain == null) {
            return null;
        }

        F1DriverPositionEntity.StateInfo entity = new  F1DriverPositionEntity.StateInfo();
        entity.setPeriod(domain.getPeriod());
        entity.setDisplayValue(domain.getDisplayValue());
        entity.setName(domain.getName());
        entity.setState(domain.getState());
        entity.setCompleted(domain.isCompleted());
        entity.setLapsCompleted(domain.getLapsCompleted());
        entity.setBehindTime(domain.getBehindTime());
        entity.setPitsTaken(domain.getPitsTaken());
        entity.setPlace(domain.getPlace());
        entity.setTotalTime(domain.getTotalTime());

        return entity;
    }


    public static F1RaceReport fromJson(String jsonString) throws Exception {
        JsonNode root = objectMapper.readTree(jsonString);
        JsonNode reportNode = root.path("report");
        F1RaceReport raceReport = new F1RaceReport();

        parseRacestrip(reportNode.path("racestrip"), raceReport);
        parseEventInfo(reportNode.path("eventInfo"), raceReport);
        parsePositions(reportNode.path("positions"), raceReport);
        System.out.println("Parsed F1RaceReport: " + raceReport);

        return raceReport;
    }


    private static void parseRacestrip(JsonNode reportNode, F1RaceReport raceReport) throws ParseException {
        System.out.println("Racestrip node: " + reportNode);

        raceReport.setRaceName(reportNode.path("name").asText());
        raceReport.setShortName(reportNode.path("shortName").asText());
        raceReport.setStartDate(parseDate(reportNode.path("date").asText()));
        raceReport.setEndDate(parseDate(reportNode.path("endDate").asText()));
        raceReport.setSeason(reportNode.path("season").asInt());

        System.out.println("Parsed racestrip: " + raceReport);
    }

    private static void parseEventInfo(JsonNode eventInfoNode, F1RaceReport raceReport) {
        F1RaceReport.EventInfo eventInfo = new F1RaceReport.EventInfo();
        F1RaceReport.Venue venue = new F1RaceReport.Venue();

        JsonNode venueNode = eventInfoNode.path("venue");
        venue.setId(venueNode.path("id").asText());
        venue.setFullName(venueNode.path("fullName").asText());
        venue.setLength(venueNode.path("length").asDouble());
        venue.setCountryFlag(parseImage(venueNode.path("countryFlag")));
        venue.setCircuitDiagram(parseImage(venueNode.path("circuitDiagram")));
        F1RaceReport.Address address = new F1RaceReport.Address();
        JsonNode addressNode = venueNode.path("address");
        address.setCity(addressNode.path("city").asText());
        address.setCountry(addressNode.path("country").asText());
        venue.setAddress(address);

        eventInfo.setVenue(venue);
        raceReport.setEventInfo(eventInfo);
    }

    private static void parsePositions(JsonNode positionsNode, F1RaceReport raceReport) {
        List<F1DriverPosition> positions = new ArrayList<>();

        // Get the first race (index 0)
        JsonNode racePositions = positionsNode.get(0);

        // Now iterate through the positions of this race
        JsonNode driversPositions = racePositions.path("positions");
        for (JsonNode driverPos : driversPositions) {
            F1DriverPosition driverPosition = new F1DriverPosition();
            driverPosition.setOrder(driverPos.path("order").asInt());
            driverPosition.setWinner(driverPos.path("winner").asBoolean()); // Assuming position 1 is the winner
            driverPosition.setType(driverPos.path("type").asText());
            driverPosition.setRaceType(driverPos.path("raceType").asText());
            driverPosition.setDriverInfo(driverPos.path("athleteInfo").path("id").asLong());
            driverPosition.setTeam(driverPos.path("athleteInfo").path("team").asText());
            driverPosition.setTeamColor(driverPos.path("athleteInfo").path("teamColor").asText());
            driverPosition.setDisplayName(driverPos.path("athleteInfo").path("displayName").asText());
            JsonNode stateInfoNode = driverPos.path("stateInfo");
            if (!stateInfoNode.isMissingNode()) {
                driverPosition.setStateInfo(parseStateInfo(stateInfoNode));
            }

            positions.add(driverPosition);
        }
        raceReport.setPositions(positions);
    }


    private static F1RaceReport.StateInfo parseStateInfo(JsonNode stateInfoNode) {
        F1RaceReport.StateInfo stateInfo = new F1RaceReport.StateInfo();
        stateInfo.setPeriod(stateInfoNode.path("period").asInt());
        stateInfo.setDisplayValue(stateInfoNode.path("displayValue").asText());
        stateInfo.setName(stateInfoNode.path("name").asText());
        stateInfo.setState(stateInfoNode.path("state").asText());
        stateInfo.setCompleted(stateInfoNode.path("completed").asBoolean());
        stateInfo.setLapsCompleted(stateInfoNode.path("lapsCompleted").asText());
        stateInfo.setBehindTime(stateInfoNode.path("behindTime").asText());
        stateInfo.setPitsTaken(stateInfoNode.path("pitsTaken").asText());
        stateInfo.setPlace(stateInfoNode.path("place").asText());
        stateInfo.setTotalTime(stateInfoNode.path("totalTime").asText());
        return stateInfo;
    }

    private static ZonedDateTime parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME
                .withZone(ZoneOffset.UTC);
        return ZonedDateTime.parse(dateString, formatter);
    }

    private static F1RaceReport.Image parseImage(JsonNode imageNode) {
        System.out.println("Parsing image: " + imageNode);

        F1RaceReport.Image image = new F1RaceReport.Image();
        image.setHref(imageNode.path("href").asText());
        image.setWidth(imageNode.path("width").asInt());
        image.setHeight(imageNode.path("height").asInt());
        image.setAlt(imageNode.path("alt").asText());

        System.out.println("Parsed image: " + image);
        return image;
    }

}
