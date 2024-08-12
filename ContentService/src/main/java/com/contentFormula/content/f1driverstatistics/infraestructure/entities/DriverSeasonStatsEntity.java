package com.contentFormula.content.f1driverstatistics.infraestructure.entities;

import com.contentFormula.content.f1driverinfo.infraestructure.entities.DriverInfoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "f1_driver_season_stats")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DriverSeasonStatsEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Integer rank;

    @Column(nullable = false)
    private Integer starts;

    @Column(nullable = false)
    private Integer wins;

    @Column(nullable = false)
    private Integer poles;

    @Column(name = "top_5", nullable = false)
    private Integer top5;

    @Column(name = "top_10", nullable = false)
    private Integer top10;

    @Column(nullable = false)
    private Integer points;

    @Column(name = "avg_start")
    private String avgStart;

    @Column(name = "avg_finish")
    private String avgFinish;


    //The id is not the one given by the API. It is the one from my database.
    @ManyToOne()
    @JoinColumn(name = "driver_info_identifier", referencedColumnName = "driver_identifier")
    private DriverInfoEntity driverInfo;

}