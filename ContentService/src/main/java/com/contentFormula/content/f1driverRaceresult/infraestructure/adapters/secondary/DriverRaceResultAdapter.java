package com.contentFormula.content.f1driverRaceresult.infraestructure.adapters.secondary;

import com.contentFormula.content.f1driverRaceresult.domain.model.DriverRaceResult;
import com.contentFormula.content.f1driverinfo.domain.model.DriverInfo;
import com.contentFormula.content.f1driverinfo.infraestructure.adapters.secondary.DriverInfoMapper;
import com.contentFormula.content.f1driverinfo.infraestructure.adapters.secondary.JpaDriverInfoRepository;
import com.contentFormula.content.f1driverinfo.infraestructure.entities.DriverInfoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DriverRaceResultAdapter {

    private final JpaDriverRaceResultRepositoryAdapter jpaDriverRaceResultRepositoryAdapter;
    private final HttpClient httpClient;
    private final JpaDriverInfoRepository jpaDriverInfoRepository;

    public void fetchAndSaveAllDriverRaceResults() {
        // Fetch all driver IDs
        List<Long> driverIds = jpaDriverInfoRepository.findAllDriverIds();

        // Iterate over each driver ID
        for (Long driverId : driverIds) {
            fetchAndSaveDriverRaceResults(driverId);
        }
    }

    private void fetchAndSaveDriverRaceResults(Long driverId) {
        Optional<DriverInfoEntity> driverInfoOptional = jpaDriverInfoRepository.findById(driverId);
        if (driverInfoOptional.isEmpty()) {
            throw new RuntimeException("Driver not found");
        }
        DriverInfo driverInfo = DriverInfoMapper.toDomain(driverInfoOptional.get());
        Optional<List<DriverRaceResult>> resultsOptional = fetchDriverRaceResults(driverId);
        if (resultsOptional.isPresent()) {
            List<DriverRaceResult> results = resultsOptional.get();
            for (DriverRaceResult result : results) {
                // Check if the result already exists in the database
                Optional<DriverRaceResult> existingResult = jpaDriverRaceResultRepositoryAdapter.findByRaceNameAndDriverInfoId(result.getRace(),driverInfo.getId());
                if (existingResult.isEmpty()) {
                    // Only add the result if it doesn't exist
                    result.setDriverInfo(driverInfo);
                    jpaDriverRaceResultRepositoryAdapter.save(result);
                }
            }
        } else {
            throw new RuntimeException("Driver race results not found");
        }
    }
        private Optional<List<DriverRaceResult>> fetchDriverRaceResults(Long driverId) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://f1-motorsport-data.p.rapidapi.com/race-results?driverId=" + driverId))
                    .header("x-rapidapi-key", "a3344a18efmsh1df8778ab3f8611p13f0d9jsn9d5e72743ec2")
                    .header("x-rapidapi-host", "f1-motorsport-data.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            List<DriverRaceResult> results = DriverRaceResultMapper.fromJson(response.body());
            return Optional.of(results);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}