package com.contentFormula.content.f1racereport.infraestructure.adapters.secondary;

import com.contentFormula.content.f1news.domain.model.F1News;
import com.contentFormula.content.f1news.infraestructure.adapters.secondary.F1NewsMapper;
import com.contentFormula.content.f1racereport.domain.model.F1RaceReport;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import org.slf4j.Logger;

@Service
@RequiredArgsConstructor
public class F1RaceReportAdapter {
    private final JpaF1RaceReportAdapter jpaF1RaceReportAdapter;
    private final HttpClient httpClient;
    private static final Logger logger = LoggerFactory.getLogger(F1RaceReportAdapter.class);

    @Value("${motorsports.api.key}")
    private String apiKey;

    public void fetchAndSave(String eventId){
        logger.info("Fetching race report for event ID: {}", eventId);
        Optional<F1RaceReport> raceReportOptional = fetchRaceReport(eventId);
        if(raceReportOptional.isPresent()){
            F1RaceReport f1RaceReport = raceReportOptional.get();
            logger.info("Race report fetched successfully. Saving to database.");
            jpaF1RaceReportAdapter.saveRaceReport(f1RaceReport);
            logger.info("Race report saved successfully.");
        } else {
            logger.error("Race report not found for event ID: {}", eventId);
            throw new RuntimeException("Race report not found for event ID: " + eventId);
        }
    }

    private Optional<F1RaceReport> fetchRaceReport(String eventId) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://f1-motorsport-data.p.rapidapi.com/race-report?eventId="+eventId))
                    .header("x-rapidapi-key", apiKey)
                    .header("x-rapidapi-host", "f1-motorsport-data.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("API Response Status Code: " + response.statusCode());
            if (response.statusCode() != 200) {
                throw new RuntimeException("API request failed with status code: " + response.statusCode());
            }

            F1RaceReport f1RaceReport = F1RaceReportMapper.fromJson(response.body());
            return Optional.of(f1RaceReport);
        } catch (Exception e) {
            System.err.println("Error fetching race report: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
