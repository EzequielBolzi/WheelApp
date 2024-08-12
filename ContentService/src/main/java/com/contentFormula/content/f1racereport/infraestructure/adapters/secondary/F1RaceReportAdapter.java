package com.contentFormula.content.f1racereport.infraestructure.adapters.secondary;

import com.contentFormula.content.f1news.domain.model.F1News;
import com.contentFormula.content.f1news.infraestructure.adapters.secondary.F1NewsMapper;
import com.contentFormula.content.f1racereport.domain.model.F1RaceReport;
import com.contentFormula.content.f1schedule.domain.model.F1Schedule;
import com.contentFormula.content.f1schedule.infraestructure.adapters.secondary.JpaF1ScheduleAdapter;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
@Service
@RequiredArgsConstructor
public class F1RaceReportAdapter {
    private final JpaF1RaceReportAdapter jpaF1RaceReportAdapter;
    private final JpaF1ScheduleAdapter jpaF1ScheduleAdapter;
    private final HttpClient httpClient;
    private static final Logger logger = LoggerFactory.getLogger(F1RaceReportAdapter.class);

    @Value("${motorsports.api.key}")
    private String apiKey;

    public void fetchAndSave() {
        List<F1Schedule> schedules = jpaF1ScheduleAdapter.getAllSchedule();
        if (schedules == null || schedules.isEmpty()) {
            logger.warn("No schedules found to process");
            return;
        }

        for (F1Schedule eachSchedule : schedules) {
            try {
                processSchedule(eachSchedule);
            } catch (Exception e) {
                logger.error("Error processing schedule with ID: " + eachSchedule.getRaceId(), e);
            }
        }
    }

    private void processSchedule(F1Schedule schedule) {
        String eventId = schedule.getRaceId();
        Optional<F1RaceReport> existingReport = jpaF1RaceReportAdapter.getRaceReportByRaceId(eventId);
        if (existingReport.isPresent()) {
            logger.info("Race report already exists for event ID: {}", eventId);
            return;
        }

        Optional<F1RaceReport> raceReportOptional = fetchRaceReport(eventId);
        if (raceReportOptional.isPresent()) {
            F1RaceReport f1RaceReport = raceReportOptional.get();
            jpaF1RaceReportAdapter.saveRaceReport(f1RaceReport,schedule.getRaceId());
            logger.info("Race report saved successfully for event ID: {}", eventId);
        } else {
            logger.error("Race report not found for event ID: {}", eventId);
        }
    }

    private Optional<F1RaceReport> fetchRaceReport(String eventId) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://f1-motorsport-data.p.rapidapi.com/race-report?eventId=" + eventId))
                    .header("x-rapidapi-key", apiKey)
                    .header("x-rapidapi-host", "f1-motorsport-data.p.rapidapi.com")
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                logger.error("API request failed with status code: {} for event ID: {}", response.statusCode(), eventId);
                return Optional.empty();
            }

            F1RaceReport f1RaceReport = F1RaceReportMapper.fromJson(response.body());
            return Optional.of(f1RaceReport);
        } catch (Exception e) {
            logger.error("Error fetching race report for event ID: " + eventId, e);
            return Optional.empty();
        }
    }
}
