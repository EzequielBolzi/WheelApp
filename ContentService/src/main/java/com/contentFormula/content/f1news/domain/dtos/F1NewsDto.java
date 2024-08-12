package com.contentFormula.content.f1news.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class F1NewsDto {
    private String headline;
    private String newsBody;
    private List<F1NewsDto.ImageInfoDto> images;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class ImageInfoDto {
        private String credit;
        private String url;
    }
}
