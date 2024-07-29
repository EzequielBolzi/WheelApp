package com.contentFormula.content.apicontent.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vehicle {
    private String number;
    private String manufacturer;
    private String chassis;
    private String engine;
    private String tire;
    private String team;
    private DriverInfo driverInfo;
}
