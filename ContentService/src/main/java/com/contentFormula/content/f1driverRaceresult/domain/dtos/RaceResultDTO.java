package com.contentFormula.content.f1driverRaceresult.domain.dtos;

import lombok.Data;

@Data
public class RaceResultDTO {
    private String date;
    private String race;
    private Integer place;
    private Integer start;
    private Integer laps;
    private Integer points;
}