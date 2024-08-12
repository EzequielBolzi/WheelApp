package com.contentFormula.content.f1driverinfo.infraestructure.adapters.secondary;

import com.contentFormula.content.f1driverinfo.domain.model.DriverInfo;
import com.contentFormula.content.f1driverinfo.infraestructure.customexceptions.CustomUpdatedDriverSuccesfullyException;
import com.contentFormula.content.f1racereport.infraestructure.adapters.secondary.JpaF1RaceReportAdapter;
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
public class DriverInfoAdapter {

    private final JpaDriverInfoAdapter jpaDriverInfoAdapter;
    private final JpaF1RaceReportAdapter jpaF1RaceReportAdapter;
    private final HttpClient httpClient;

    // Extract Information about Driver from EXTERNAL API
    public void fetchAndSaveDriverInfo() {
        Optional<List<Long>> existingDrivers = jpaF1RaceReportAdapter.getDrivers();
        List<Long> driversToFetch = existingDrivers.get();
        System.out.println("LISTA DE DRIVERS"+ driversToFetch);
        for(Long eachDriver: driversToFetch){
            System.out.println("Driver id: "+ eachDriver);
            Optional<DriverInfo> existingDriverInfoOptional = jpaDriverInfoAdapter.getDriverInfo(eachDriver);
            Optional<DriverInfo> newDriverInfo = fetchDriverInfo(eachDriver);
            if (existingDriverInfoOptional.isPresent()) {
                DriverInfo existingDriverInfo = existingDriverInfoOptional.get();
                if (existingDriverInfo.equals(newDriverInfo.orElse(null))) {
                    jpaDriverInfoAdapter.updateDriverInfo(existingDriverInfo.getDriverId(), newDriverInfo.orElse(null));
                    throw new CustomUpdatedDriverSuccesfullyException("Driver information updated.");
                }
                else{
                    System.out.println("Driver existente");
                }
            }
            else {
                jpaDriverInfoAdapter.saveDriverInfo(newDriverInfo.orElse(null));
            }
        }

        }


    private Optional<DriverInfo> fetchDriverInfo(Long driverId) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://f1-motorsport-data.p.rapidapi.com/athlete-info?athleteId=" + driverId))
                    .header("x-rapidapi-key", "a3344a18efmsh1df8778ab3f8611p13f0d9jsn9d5e72743ec2")
                    .header("x-rapidapi-host", "f1-motorsport-data.p.rapidapi.com")
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 404) {
                // Driver not found in external API
                return Optional.empty();
            } else if (response.statusCode() != 200) {
                // Other API errors
                throw new RuntimeException("API error: " + response.statusCode());
            }
            DriverInfo driverInfo = DriverInfoMapper.fromJson(response.body());
            return Optional.of(driverInfo);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching driver info: " + e.getMessage(), e);
        }
    }
}