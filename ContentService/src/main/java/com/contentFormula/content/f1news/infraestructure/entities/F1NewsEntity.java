package com.contentFormula.content.f1news.infraestructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "f1_news")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class F1NewsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_source_identifier", nullable = false)
    private String dataSourceIdentifier;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "headline", nullable = false,columnDefinition = "TEXT")
    private String headline;

    @Column(name = "link", nullable = false,columnDefinition = "TEXT")
    private String link;

    @Column(name = "news_body", nullable = false,columnDefinition = "TEXT")
    private String newsBody;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "f1_news_id")
    private List<ImageInfoEntity> images;

    @Entity
    @Table(name = "f1_news_image_info")
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class ImageInfoEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "name",columnDefinition = "TEXT")
        private String name;

        @Column(name = "width")
        private Integer width;

        @Column(name = "height")
        private Integer height;

        @Column(name = "caption", columnDefinition = "TEXT")
        private String caption;

        @Column(name = "credit",columnDefinition = "TEXT")
        private String credit;

        @Column(name = "url", nullable = false,columnDefinition = "TEXT")
        private String url;
    }
}