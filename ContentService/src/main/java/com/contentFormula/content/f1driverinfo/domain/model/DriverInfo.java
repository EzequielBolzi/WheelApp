package com.contentFormula.content.f1driverinfo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class DriverInfo {
    private Long id;
    private Long driverId;
    private String fullName;
    private String displayName;
    private ZonedDateTime dateOfBirth;
    private String shortName;
    private String headshot;
    private List<Vehicle> vehicles;
    private Flag flag;
    private boolean linked;
    private boolean active;
    private Status status;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Flag {
        private String href;
        private String alt;
        private List<String> rel;

    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Status {
        private String id;
        private String name;
        private String type;
        private String abbreviation;

    }
}