package com.contentFormula.content.f1schedule.infraestructure.adapters.secondary;

import com.contentFormula.content.f1racereport.domain.model.F1RaceReport;
import com.contentFormula.content.f1schedule.domain.model.F1Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class F1ScheduleAdapter {

    private final JpaF1ScheduleAdapter jpaF1ScheduleAdapter;
    private final HttpClient httpClient;

    @Value("${motorsports.api.key}")
    private String apiKey;

    public void fetchAndSave(String year){
        Optional<List<F1Schedule>> schedule = fetchSchedule(year);
        if(schedule.isPresent()){
            List<F1Schedule> f1Schedule = schedule.get();

            for(F1Schedule eachSchedule : f1Schedule)
                jpaF1ScheduleAdapter.saveSchedule(eachSchedule);

        } else {
            throw new RuntimeException("Schedule report not found for year ID: " + year);
        }
    }
    private Optional<List<F1Schedule>> fetchSchedule(String year) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://f1-motorsport-data.p.rapidapi.com/schedule?year="+year))
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
