package com.contentFormula.content.driverRaceresult.domain.dtos;

import lombok.Data;

@Data
public class RaceResultDTO {
    private String date;
    private String race;
    private int place;
    private int start;
    private int laps;
    private int points;
}