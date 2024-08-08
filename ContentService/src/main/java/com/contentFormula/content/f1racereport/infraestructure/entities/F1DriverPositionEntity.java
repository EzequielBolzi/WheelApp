package com.contentFormula.content.f1racereport.infraestructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "f1_race_report_driver_position")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class F1DriverPositionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "position_order")
    private Integer order;

    @Column(name = "is_winner")
    private boolean winner;

    @Column(name = "position_type")
    private String type;

    @Column(name = "race_type")
    private String raceType;

    @Column(name = "driver_id")
    private Long driverInfo;

    @Column(name = "driver_name")
    private String  displayName;

    @Column(name = "team")
    private String team;

    @Column(name = "team_color")
    private String teamColor;

    @Embedded
    private StateInfo stateInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "f1_race_report_id")
    private F1RaceReportEntity raceReport;

    @Embeddable
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StateInfo {
        @Column(name = "period")
        private int period;

        @Column(name = "display_value")
        private String displayValue;

        @Column(name = "state_name")
        private String name;

        @Column(name = "state")
        private String state;

        @Column(name = "completed")
        private boolean completed;

        @Column(name = "laps_completed")
        private String lapsCompleted;

        @Column(name = "behind_time")
        private String behindTime;

        @Column(name = "pits_taken")
        private String pitsTaken;

        @Column(name = "place")
        private String place;

        @Column(name = "total_time")
        private String totalTime;
    }
}