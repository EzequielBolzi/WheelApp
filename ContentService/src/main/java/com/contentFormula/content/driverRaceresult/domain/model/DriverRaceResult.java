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
    private Integer place;
    private Integer startPosition;
    private Integer laps;
    private Integer points;
    private DriverInfo driverInfo;


}
