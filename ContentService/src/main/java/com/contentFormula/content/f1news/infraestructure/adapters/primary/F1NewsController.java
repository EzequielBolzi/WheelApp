package com.contentFormula.content.f1news.infraestructure.adapters.primary;


import com.contentFormula.content.f1news.infraestructure.adapters.secondary.F1NewsAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/f1_news")
@RequiredArgsConstructor
public class F1NewsController {

    private final F1NewsAdapter f1NewsAdapter;


    @GetMapping("/fetch-and-save")
    public ResponseEntity<Void> fetchAndSaveNewsInfo() {
        f1NewsAdapter.fetchAndSaveNews();
        return ResponseEntity.ok().build();
    }

}
