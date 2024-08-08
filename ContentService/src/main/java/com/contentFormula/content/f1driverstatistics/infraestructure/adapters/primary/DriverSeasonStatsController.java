package com.contentFormula.content.f1driverstatistics.infraestructure.adapters.primary;


import com.contentFormula.content.f1driverinfo.application.service.DriverInfoService;
import com.contentFormula.content.f1driverstatistics.application.service.DriverSeasonStatsService;
import com.contentFormula.content.f1driverstatistics.domain.dtos.DriverSeasonStatsDto;
import com.contentFormula.content.f1driverstatistics.domain.model.DriverSeasonStats;
import com.contentFormula.content.f1driverstatistics.infraestructure.adapters.secondary.DriverSeasonStatsAdapter;
import com.contentFormula.content.f1driverstatistics.infraestructure.adapters.secondary.DriverSeasonStatsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/api/v1/f1_driver_season_stats")
@RequiredArgsConstructor
public class DriverSeasonStatsController {
    @Autowired
    private final DriverSeasonStatsAdapter driverSeasonStatsAdapter;
    @Autowired
    private final DriverSeasonStatsService driverSeasonStatsService;
    @Autowired
    private final DriverInfoService driverInfoService;

    // Fetch information from external API to my DB
    @GetMapping("/fetch-and-save/{driverId}")
    public ResponseEntity<Void> fetchAndSaveStatsInfo(@PathVariable Long driverId) {
        driverSeasonStatsAdapter.fetchAndSaveDriverSeasonStats(driverId);
        return ResponseEntity.ok().build();
    }
    // Get all information wit.
    @GetMapping("/{year}/{driverId}")
    public ResponseEntity<DriverSeasonStatsDto> getStatsByYearAndDriver(@PathVariable Integer year, @PathVariable Long driverId) {
        Optional<DriverSeasonStats> byYearAndDriverInfoId = driverSeasonStatsService.findByYearAndDriverInfo_Id(year, driverInfoService.getDriverInfo(driverId).get().getId());
        if (byYearAndDriverInfoId.isPresent()) {
            return ResponseEntity.ok(DriverSeasonStatsMapper.toDto(byYearAndDriverInfoId.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
