package com.contentFormula.content.f1driverRaceresult.infraestructure.adapters.primary;

import com.contentFormula.content.f1driverRaceresult.domain.dtos.RaceResultDTO;
import com.contentFormula.content.f1driverRaceresult.domain.model.DriverRaceResult;
import com.contentFormula.content.f1driverRaceresult.infraestructure.adapters.secondary.DriverRaceResultAdapter;
import com.contentFormula.content.f1driverRaceresult.infraestructure.adapters.secondary.DriverRaceResultMapper;
import com.contentFormula.content.f1driverRaceresult.infraestructure.adapters.secondary.JpaDriverRaceResultRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/f1_race_results")
@RequiredArgsConstructor
public class DriverRaceResultController {

    @Autowired
    private final DriverRaceResultAdapter  driverRaceResultAdapter;
    @Autowired
    private final JpaDriverRaceResultRepositoryAdapter jpaDriverRaceResultRepositoryAdapter;



    // Fetch information from external API to my DB
    @GetMapping("/fetch-and-save/{driverId}")
    public ResponseEntity<Void> fetchAndSaveDriverInfo(@PathVariable Long driverId) {
        driverRaceResultAdapter.fetchAndSaveDriverRaceResults(driverId);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{driverName}")
    public ResponseEntity<List<RaceResultDTO>> getDriverRaceResults(@PathVariable String driverName) {
       List<DriverRaceResult> domainResults = jpaDriverRaceResultRepositoryAdapter.findResultsByFullName(driverName);
        if (domainResults.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<RaceResultDTO> results = domainResults.stream()
                .map(DriverRaceResultMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(results);
    }


}
