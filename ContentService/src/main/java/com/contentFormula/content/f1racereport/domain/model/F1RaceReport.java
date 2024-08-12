package com.contentFormula.content.f1racereport.domain.model;

import com.contentFormula.content.f1driverinfo.domain.model.DriverInfo;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class F1RaceReport {
    private Long id;
    private String raceEventId;
    private String raceName;
    private String shortName;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private Integer season;
    private List<F1DriverPosition> positions;
    private EventInfo eventInfo;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Image {
        private String href;
        private Integer width;
        private Integer height;
        private String alt;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EventInfo {
        private Venue venue;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Venue {
        private String id;
        private String fullName;
        private Address address;
        private double length;
        private Image countryFlag;
        private Image circuitDiagram;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Address {
        private String city;
        private String country;
    }



    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StateInfo {
        private Integer period;
        private String displayValue;
        private String name;
        private String state;
        private boolean completed;
        private String lapsCompleted;
        private String behindTime;
        private String pitsTaken;
        private String place;
        private String totalTime;
    }
}