package com.contentFormula.content.driverinfo.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DriverInfoDto {
    private String fullName;
    private int  age;
    private String headshot;
    private String team;
    private String alt;

}
