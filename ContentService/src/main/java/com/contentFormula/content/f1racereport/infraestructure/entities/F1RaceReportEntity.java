package com.contentFormula.content.f1racereport.infraestructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "f1_race_report")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class F1RaceReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "race_name")
    private String raceName;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "start_date")
    private ZonedDateTime startDate;

    @Column(name = "end_date")
    private ZonedDateTime endDate;

    @Column(name = "season")
    private Integer season;

    @OneToMany(mappedBy = "raceReport", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<F1DriverPositionEntity> positions;

    @Embedded
    private EventInfo eventInfo;

    @Embeddable
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EventInfo {
        @Embedded
        private Venue venue;
    }

    @Embeddable
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Venue {
        @Column(name = "venue_id")
        private String id;

        @Column(name = "circuit_full_name")
        private String fullName;

        @Embedded
        private Address address;

        @Column(name = "circuit_length")
        private double length;

        @Embedded
        @AttributeOverrides({
                @AttributeOverride(name = "href", column = @Column(name = "country_flag_href")),
                @AttributeOverride(name = "alt", column = @Column(name = "country_flag_alt"))
        })
        private Image countryFlag;

        @Embedded
        @AttributeOverrides({
                @AttributeOverride(name = "href", column = @Column(name = "circuit_diagram_href")),
                @AttributeOverride(name = "alt", column = @Column(name = "circuit_diagram_alt"))
        })
        private Image circuitDiagram;
    }

    @Embeddable
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Address {
        @Column(name = "city")
        private String city;

        @Column(name = "country")
        private String country;
    }

    @Embeddable
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Image {
        @Column(name = "image_href")
        private String href;

        @Column(name = "image_alt")
        private String alt;
    }
}