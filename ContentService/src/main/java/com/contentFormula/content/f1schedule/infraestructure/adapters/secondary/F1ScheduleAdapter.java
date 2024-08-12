package com.contentFormula.content.f1schedule.infraestructure.adapters.secondary;

import com.contentFormula.content.f1schedule.domain.model.F1Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class F1ScheduleAdapter {

    private final JpaF1ScheduleAdapter jpaF1ScheduleAdapter;
    private final HttpClient httpClient;

    @Value("${motorsports.api.key}")
    private String apiKey;


    public void fetchAndSave() {
        int currentYear = Year.now().getValue();
        // Process all the schedule
        if (jpaF1ScheduleAdapter.getScheduleByYear(currentYear).isEmpty()) {
            for (int year = 1950; year <= currentYear; year++) {
                processYear(year);
            }
        } else {
            // Process current year only.
            processYear(currentYear);
        }
    }

    private void processYear(int year) {
        String yearString = Integer.toString(year);
        Optional<List<F1Schedule>> schedule = fetchSchedule(yearString);
        if (schedule.isPresent()) {
            List<F1Schedule> f1Schedule = schedule.get();
            for (F1Schedule eachSchedule : f1Schedule) {
                System.out.println("Processing schedule for date: " + eachSchedule.getStartDate());

                if (eachSchedule.getStartDate() == null) {
                    System.out.println("Warning: Schedule has null start date. Skipping...");
                    continue;
                }
                Optional<F1Schedule> existingSchedule = jpaF1ScheduleAdapter.getScheduleByDate(eachSchedule.getStartDate());

                if (existingSchedule.isEmpty()) {
                    System.out.println("Saving new schedule");
                    jpaF1ScheduleAdapter.saveSchedule(eachSchedule);
                } else {
                    F1Schedule existing = existingSchedule.get();
                    if (existing.getId() == null) {
                        System.out.println("Warning: Existing schedule has null ID. Skipping update...");
                    } else if (!existing.isCompleted()) {
                        System.out.println("Updating existing schedule with ID: " + existing.getId());
                        jpaF1ScheduleAdapter.updateSchedule(eachSchedule, existing.getId());
                    } else {
                        System.out.println("Existing schedule is already completed. Skipping update.");
                    }
                }
            }
        } else {
            System.out.println("No schedule data found for year: " + year);
        }
    }


    private Optional<List<F1Schedule>> fetchSchedule(String year) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://f1-motorsport-data.p.rapidapi.com/schedule?year=" + year))
                    .header("x-rapidapi-key", apiKey)
                    .header("x-rapidapi-host", "f1-motorsport-data.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("API Response Status Code: " + response.statusCode());
            if (response.statusCode() != 200) {
                throw new RuntimeException("API request failed with status code: " + response.statusCode());
            }

            List<F1Schedule> f1Schedules = F1ScheduleMapper.fromJson(response.body());
            return Optional.of(f1Schedules);
        } catch (Exception e) {
            System.err.println("Error fetching schedule report: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
