package com.contentFormula.content.f1news.infraestructure.adapters.secondary;


import com.contentFormula.content.f1news.domain.model.F1News;
import com.contentFormula.content.f1news.infraestructure.entities.F1NewsEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class F1NewsMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static F1News toDomain(F1NewsEntity entity) {
        if (entity == null) return null;

        List<F1News.ImageInfo> images = entity.getImages().stream()
                .map(F1NewsMapper::toDomain)
                .collect(Collectors.toList());

        return new F1News(
                entity.getId(),
                entity.getDataSourceIdentifier(),
                entity.getDescription(),
                entity.getHeadline(),
                entity.getLink(),
                entity.getNewsBody(),
                images
        );
    }

    public static F1NewsEntity toEntity(F1News domain) {
        if (domain == null) return null;

        List<F1NewsEntity.ImageInfoEntity> images = domain.getImages().stream()
                .map(F1NewsMapper::toEntity)
                .collect(Collectors.toList());

        return new F1NewsEntity(
                domain.getId(),
                domain.getDataSourceIdentifier(),
                domain.getDescription(),
                domain.getHeadline(),
                domain.getLink(),
                domain.getNewsBody(),
                images
        );
    }

    public static F1News.ImageInfo toDomain(F1NewsEntity.ImageInfoEntity entity) {
        if (entity == null) return null;

        return new F1News.ImageInfo(
                entity.getId(),
                entity.getName(),
                entity.getWidth(),
                entity.getHeight(),
                entity.getCaption(),
                entity.getCredit(),
                entity.getUrl()
        );
    }

    public static F1NewsEntity.ImageInfoEntity toEntity(F1News.ImageInfo domain) {
        if (domain == null) return null;

        return new F1NewsEntity.ImageInfoEntity(
                domain.getId(),  // ID is usually auto-generated
                domain.getName(),
                domain.getWidth(),
                domain.getHeight(),
                domain.getCaption(),
                domain.getCredit(),
                domain.getUrl()
        );
    }

    public static List<F1News> fromJson(String jsonString) throws Exception {
        JsonNode root = objectMapper.readTree(jsonString);
        List<F1News> newsList = new ArrayList<>();
        if (root.isArray()) {
            for (JsonNode node : root) {
                F1News news = new F1News(
                        null,
                        node.path("dataSourceIdentifier").asText(),
                        node.path("description").asText(),
                        node.path("headline").asText(),
                        node.path("link").asText(),
                        "",
                        node.path("images").isArray() ?
                                parseImages(node.path("images")) : new ArrayList<>()
                );
                newsList.add(news);
            }
        }
        return newsList;
    }

    private static List<F1News.ImageInfo> parseImages(JsonNode imagesNode) {
        List<F1News.ImageInfo> images = new ArrayList<>();
        for (JsonNode imageNode : imagesNode) {
            F1News.ImageInfo imageInfo = new F1News.ImageInfo(
                    null,
                    imageNode.path("name").asText(),
                    imageNode.path("width").asInt(),
                    imageNode.path("height").asInt(),
                    imageNode.path("caption").asText(),
                    imageNode.path("credit").asText(),
                    imageNode.path("url").asText()
            );
            images.add(imageInfo);
        }
        return images;
    }

}