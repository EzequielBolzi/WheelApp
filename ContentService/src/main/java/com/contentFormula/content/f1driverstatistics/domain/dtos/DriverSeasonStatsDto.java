package com.contentFormula.content.f1driverstatistics.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverSeasonStatsDto {
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
    private String nameDriver;
}