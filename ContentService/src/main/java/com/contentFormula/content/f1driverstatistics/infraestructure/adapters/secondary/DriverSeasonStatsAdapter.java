package com.contentFormula.content.f1driverstatistics.infraestructure.adapters.secondary;

import com.contentFormula.content.f1driverinfo.domain.model.DriverInfo;
import com.contentFormula.content.f1driverinfo.infraestructure.adapters.secondary.DriverInfoMapper;
import com.contentFormula.content.f1driverinfo.infraestructure.adapters.secondary.JpaDriverInfoRepository;
import com.contentFormula.content.f1driverinfo.infraestructure.entities.DriverInfoEntity;
import com.contentFormula.content.f1driverstatistics.domain.model.DriverSeasonStats;
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
public class DriverSeasonStatsAdapter {
    private final JpaDriverSeasonStatsRepositoryAdapter jpaDriverSeasonStatsRepositoryAdapter;
    private final HttpClient httpClient;
    private final JpaDriverInfoRepository jpaDriverInfoRepository;

    public void fetchAndSaveAllDriverSeasoStats() {
        // Fetch all driver IDs
        List<Long> driverIds = jpaDriverInfoRepository.findAllDriverIds();

        // Iterate over each driver ID
        for (Long driverId : driverIds) {
            fetchAndSaveDriverSeasonStats(driverId);
        }
    }

    private void fetchAndSaveDriverSeasonStats(Long driverId) {
        Optional<DriverInfoEntity> driverInfoOptional = jpaDriverInfoRepository.findById(driverId);
        DriverInfo driverInfo = DriverInfoMapper.toDomain(driverInfoOptional.get());
        Optional<List<DriverSeasonStats>> statsOptional = fetchDriverSeasonStats(driverId);
        if (statsOptional.isPresent()) {
            List<DriverSeasonStats> stats = statsOptional.get();
            for (DriverSeasonStats stat : stats) {
                // Check if the stat already exists in the database
                Optional<DriverSeasonStats> existingStat = jpaDriverSeasonStatsRepositoryAdapter.getByYearAndDriverInfo_Id(stat.getYear(), driverInfo.getId());
                if (existingStat.isEmpty()) {
                    // Only add the stat if it doesn't exist
                    stat.setDriverInfo(driverInfo);
                    jpaDriverSeasonStatsRepositoryAdapter.save(stat);
                }
            }
        } else {
            throw new RuntimeException("Driver season stats not found");
        }
    }

    private Optional<List<DriverSeasonStats>> fetchDriverSeasonStats(Long driverId) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://f1-motorsport-data.p.rapidapi.com/stats?driverId=" + driverId))
                    .header("x-rapidapi-key", "a3344a18efmsh1df8778ab3f8611p13f0d9jsn9d5e72743ec2")
                    .header("x-rapidapi-host", "f1-motorsport-data.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            List<DriverSeasonStats> stats = DriverSeasonStatsMapper.fromJson(response.body());
            return Optional.of(stats);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}