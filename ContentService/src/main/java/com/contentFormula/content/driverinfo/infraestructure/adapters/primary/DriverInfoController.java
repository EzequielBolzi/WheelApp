package com.contentFormula.content.driverinfo.infraestructure.adapters.primary;

import com.contentFormula.content.driverinfo.application.service.DriverInfoService;
import com.contentFormula.content.driverinfo.domain.dtos.DriverInfoDto;
import com.contentFormula.content.driverinfo.domain.model.DriverInfo;
import com.contentFormula.content.driverinfo.infraestructure.adapters.secondary.DriverInfoMapper;
import com.contentFormula.content.driverinfo.infraestructure.adapters.secondary.DriverInfoAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/driver")
@RequiredArgsConstructor
public class DriverInfoController {

    @Autowired
    private final DriverInfoAdapter driverInfoServiceClient;

    @Autowired
    private final DriverInfoService driverInfoService;



    // Fetch information from external API to my DB
    @GetMapping("/fetch-and-save/{driverId}")
    public ResponseEntity<Void> fetchAndSaveDriverInfo(@PathVariable Long driverId) {
        driverInfoServiceClient.fetchAndSaveDriverInfo(driverId);
        return ResponseEntity.ok().build();
    }


    // Get all information without filtering.
    @GetMapping("/all")
    public ResponseEntity<List<DriverInfoDto>> getAllDrivers() {
        List<DriverInfoDto> drivers = DriverInfoMapper.toDto(driverInfoService.getAllDrivers());
        return ResponseEntity.ok(drivers);
    }


    // Get  driver  information provided from DriverInfoDto
    @GetMapping("/{driverName}")
    public ResponseEntity<DriverInfo> getDriverByName(@PathVariable String driverName) {
        return driverInfoService.getDriverByName(driverName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    // Get all drivers from a team
    @GetMapping("/team/{teamName}")
    public ResponseEntity<List<DriverInfoDto>> getDriversPerTeam(@PathVariable String teamName) {
        return ResponseEntity.ok(driverInfoService.getDriversPerTeam(teamName)
                .stream()
                .map(DriverInfoMapper::toDto)
                .toList());
    }
}