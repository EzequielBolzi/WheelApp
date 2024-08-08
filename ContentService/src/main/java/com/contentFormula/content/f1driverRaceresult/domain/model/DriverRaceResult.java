package com.contentFormula.content.f1driverRaceresult.domain.model;

import com.contentFormula.content.f1driverinfo.domain.model.DriverInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
