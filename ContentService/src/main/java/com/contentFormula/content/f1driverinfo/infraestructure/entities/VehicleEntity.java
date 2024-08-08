package com.contentFormula.content.f1driverinfo.infraestructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "f1_vehicle")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private Long id;

    @Column(name = "vehicle_number")
    private String number;

    @Column(name = "vehicle_manufacturer")
    private String manufacturer;

    @Column(name = "vehicle_chassis")
    private String chassis;

    @Column(name = "vehicle_engine")
    private String engine;

    @Column(name = "vehicle_tire")
    private String tire;

    @Column(name = "vehicle_team")
    private String team;

    @ManyToOne
    @JoinColumn(name = "driver_info_id")
    private DriverInfoEntity driverInfo;
}
