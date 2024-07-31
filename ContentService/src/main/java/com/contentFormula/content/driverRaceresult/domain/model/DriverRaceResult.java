package com.contentFormula.content.driverRaceresult.domain.model;

import com.contentFormula.content.driverinfo.domain.model.DriverInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DriverRaceResult {

    private Long id;
    private String date;
    private String race;
    private int place;
    private int startPosition;
    private int laps;
    private int points;
    private DriverInfo driverInfo;


}
