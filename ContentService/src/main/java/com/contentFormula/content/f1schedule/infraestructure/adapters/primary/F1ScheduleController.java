package com.contentFormula.content.f1schedule.infraestructure.adapters.primary;

import com.contentFormula.content.f1driverstatistics.domain.dtos.DriverSeasonStatsDto;
import com.contentFormula.content.f1driverstatistics.domain.model.DriverSeasonStats;
import com.contentFormula.content.f1driverstatistics.infraestructure.adapters.secondary.DriverSeasonStatsMapper;
import com.contentFormula.content.f1schedule.application.service.F1ScheduleService;
import com.contentFormula.content.f1schedule.domain.model.F1Schedule;
import com.contentFormula.content.f1schedule.domain.port.out.F1ScheduleRepositoryPort;
import com.contentFormula.content.f1schedule.infraestructure.adapters.secondary.F1ScheduleAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/f1_schedule")
@RequiredArgsConstructor
public class F1ScheduleController {

    @Autowired
    private final F1ScheduleAdapter f1ScheduleAdapter;

    @Autowired
    private final F1ScheduleService f1ScheduleService;

    // Fetch information from external API to my DB
    @GetMapping("/fetch-and-save/{yearId}")
    public ResponseEntity<Void> fetchAndSaveDriverInfo(@PathVariable String yearId) {
        f1ScheduleAdapter.fetchAndSave(yearId);
        return ResponseEntity.ok().build();
}

    @GetMapping("/{yearId}")
    public ResponseEntity<List<F1Schedule>> getScheduleByYear(@PathVariable Integer yearId) {
        Optional<List<F1Schedule>> schedulesByYear = f1ScheduleService.getScheduleByYear(yearId);

        if (schedulesByYear.isPresent()) {
            List<F1Schedule> schedules = schedulesByYear.get();
            if (schedules.isEmpty()) {
                return ResponseEntity.noContent().build();  // No schedules found for the given year
            } else {
                return ResponseEntity.ok(schedules);  // Return the list of schedules
            }
        } else {
            return ResponseEntity.notFound().build();  // No data available for the given year
        }
    }
}
