package com.contentFormula.content.apicontent.domain.dtos;

import com.contentFormula.content.apicontent.domain.model.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

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
