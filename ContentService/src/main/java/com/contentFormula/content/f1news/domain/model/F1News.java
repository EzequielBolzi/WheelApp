package com.contentFormula.content.f1news.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class F1News {
    private Long id;
    private String dataSourceIdentifier;
    private String description;
    private String headline;
    private String link;
    private String newsBody;
    private List<ImageInfo> images;



    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class ImageInfo {
        private Long id;
        private String name;
        private Integer width;
        private Integer height;
        private String caption;
        private String credit;
        private String url;

    }

}
