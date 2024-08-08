package com.contentFormula.content.f1driverRaceresult.infraestructure.entities;

import com.contentFormula.content.f1driverinfo.infraestructure.entities.DriverInfoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "f1_driver_race_result")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DriverRaceResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date")
    private String date;
    @Column(name = "race")
    private String race;
    @Column(name = "place")
    private Integer place;
    @Column(name = "start_position")
    private Integer startPosition;
    @Column(name = "laps")
    private Integer laps;
    @Column(name = "points")
    private Integer points;

    @ManyToOne()
    @JoinColumn(name = "driver_info_id")
    private DriverInfoEntity driverInfo;

}
