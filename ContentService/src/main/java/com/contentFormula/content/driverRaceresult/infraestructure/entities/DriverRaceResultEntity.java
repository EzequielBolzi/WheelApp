package com.contentFormula.content.driverRaceresult.infraestructure.entities;

import com.contentFormula.content.driverinfo.infraestructure.entities.DriverInfoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "driver_race_result")
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
    private int place;
    @Column(name = "start_position")
    private int startPosition;
    @Column(name = "laps")
    private int laps;
    @Column(name = "points")
    private int points;

    @ManyToOne()
    @JoinColumn(name = "driver_info_id")
    private DriverInfoEntity driverInfo;

}
