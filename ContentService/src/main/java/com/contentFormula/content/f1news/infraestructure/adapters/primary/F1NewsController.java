package com.contentFormula.content.f1news.infraestructure.adapters.primary;


import com.contentFormula.content.f1driverinfo.application.service.DriverInfoService;
import com.contentFormula.content.f1driverinfo.domain.dtos.DriverInfoDto;
import com.contentFormula.content.f1driverinfo.infraestructure.adapters.secondary.DriverInfoMapper;
import com.contentFormula.content.f1news.application.service.F1NewsService;
import com.contentFormula.content.f1news.domain.dtos.F1NewsDto;
import com.contentFormula.content.f1news.infraestructure.adapters.secondary.F1NewsAdapter;
import com.contentFormula.content.f1news.infraestructure.adapters.secondary.F1NewsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/f1_news")
@RequiredArgsConstructor
public class F1NewsController {

    @Autowired
    private final F1NewsAdapter f1NewsAdapter;

    @Autowired
    private final F1NewsService f1NewsService;

    @GetMapping("/fetch-and-save")
    public ResponseEntity<Void> fetchAndSaveNewsInfo() {
        f1NewsAdapter.fetchAndSaveNews();
        return ResponseEntity.ok().build();
    }
    @GetMapping("")
    public ResponseEntity<List<F1NewsDto>>getAllNews() {
        List<F1NewsDto> news = F1NewsMapper.toDto(f1NewsService.getAllNews());
        return ResponseEntity.ok(news);
    }

}
