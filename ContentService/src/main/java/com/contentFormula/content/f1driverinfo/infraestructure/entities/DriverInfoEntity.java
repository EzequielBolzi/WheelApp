package com.contentFormula.content.f1driverinfo.infraestructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "f1_driver_info")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DriverInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_identifier")
    private Long driverIdentifier;

    @Column(name = "driver_id")
    private Long driverId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "date_of_birth")
    private ZonedDateTime dateOfBirth;


    @Column(name = "short_name")
    private String shortName;

    @Column(name = "headshot")
    private String headshot;

    @OneToMany(mappedBy = "driverInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VehicleEntity> vehicles;

    @Embedded
    private Flag flag;

    @Column(name = "linked")
    private boolean linked;

    @Column(name = "active")
    private boolean active;

    @Embedded
    private Status status;

    @Embeddable
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Flag {
        private String href;
        private String alt;

        @ElementCollection
        @CollectionTable(name = "flag_rel", joinColumns = @JoinColumn(name = "driver_info_id"))
        @Column(name = "rel")
        private List<String> rel;
    }

    @Embeddable
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Status {
        @Column(name = "status_id")
        private String id;
        @Column(name = "status_name")
        private String name;
        @Column(name = "status_type")
        private String type;
        @Column(name = "status_abbreviation")
        private String abbreviation;
    }
}
