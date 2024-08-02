package com.contentFormula.content.driverstatistics.domain.model;


import com.contentFormula.content.driverinfo.domain.model.DriverInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DriverSeasonStats {

    private Long id;
    private Integer year;
    private Integer rank;
    private Integer starts;
    private Integer wins;
    private Integer poles;
    private Integer top5;
    private Integer top10;
    private Integer points;
    private String avgStart;
    private String avgFinish;
    private DriverInfo driverInfo;

}
