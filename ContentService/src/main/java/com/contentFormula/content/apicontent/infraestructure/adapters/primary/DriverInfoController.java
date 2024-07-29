package com.contentFormula.content.apicontent.infraestructure.adapters.primary;

import com.contentFormula.content.apicontent.application.service.DriverInfoService;
import com.contentFormula.content.apicontent.domain.dtos.DriverInfoDto;
import com.contentFormula.content.apicontent.domain.model.DriverInfo;
import com.contentFormula.content.apicontent.infraestructure.adapters.secondary.DriverInfoMapper;
import com.contentFormula.content.apicontent.infraestructure.adapters.secondary.DriverInfoServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/drivers")
@RequiredArgsConstructor
public class DriverInfoController {

    @Autowired
    private final DriverInfoServiceClient driverInfoServiceClient;

    @Autowired
    private final DriverInfoService driverInfoService;



    // Fetch information from external API to my DB
    @GetMapping("/fetch-and-save/{driverId}")
    public ResponseEntity<Void> fetchAndSaveDriverInfo(@PathVariable Long driverId) {
        driverInfoServiceClient.fetchAndSaveDriverInfo(driverId);
        return ResponseEntity.ok().build();
    }


    // Get all information without filtering.
    @GetMapping("")
    public ResponseEntity<List<DriverInfoDto>> getAllDrivers() {
        List<DriverInfoDto> drivers = DriverInfoMapper.toDto(driverInfoService.getAllDrivers());
        return ResponseEntity.ok(drivers);
    }


    // Get all drivers with information provided from DriverInfoDto
    @GetMapping("/{driverName}")
    public ResponseEntity<DriverInfo> getDriverByName(@PathVariable String driverName) {
        return driverInfoService.getDriverByName(driverName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }





}