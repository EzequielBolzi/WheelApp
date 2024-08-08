package com.contentFormula.content.f1racereport.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public  class F1DriverPosition {
    private Integer order;
    private boolean winner;
    private String type;
    private String raceType;
    private String team;
    private String teamColor;
    private long driverInfo;
    private String displayName;
    private F1RaceReport.StateInfo stateInfo;
}