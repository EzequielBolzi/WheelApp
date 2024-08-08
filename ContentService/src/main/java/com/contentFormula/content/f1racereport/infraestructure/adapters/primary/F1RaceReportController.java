package com.contentFormula.content.f1racereport.infraestructure.adapters.primary;


import com.contentFormula.content.f1news.infraestructure.adapters.secondary.F1NewsAdapter;
import com.contentFormula.content.f1racereport.infraestructure.adapters.secondary.F1RaceReportAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/f1_race_report")
@RequiredArgsConstructor
public class F1RaceReportController {

    @Autowired
    private final F1RaceReportAdapter f1RaceReportAdapter;


    @GetMapping("/fetch-and-save/{eventId}")
    public ResponseEntity<Void> fetchAndSaveNewsInfo(@PathVariable String eventId) {
        f1RaceReportAdapter.fetchAndSave(eventId);
        return ResponseEntity.ok().build();
    }

}
