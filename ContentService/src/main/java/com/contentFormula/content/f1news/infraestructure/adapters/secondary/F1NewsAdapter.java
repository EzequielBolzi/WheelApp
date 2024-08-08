package com.contentFormula.content.f1news.infraestructure.adapters.secondary;

import com.contentFormula.content.f1driverinfo.infraestructure.adapters.secondary.JpaDriverInfoRepository;
import com.contentFormula.content.f1news.domain.model.F1News;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class F1NewsAdapter {
    @Value("${motorsports.api.key}")
    private String apiKey;

    private final JpaF1NewsAdapter jpaF1NewsAdapter;
    private final JpaF1NewsRepository jpaF1NewsRepository;
    private final HttpClient httpClient;

    public void fetchAndSaveNews() {
        Optional<List<F1News>> newsOptional = fetchDriverNews();
        if (newsOptional.isPresent()) {
            List<F1News> news = newsOptional.get();
            for (F1News newsItem : news) {
                //Avoid repeated news
                if(jpaF1NewsRepository.findByDataSourceIdentifier( newsItem.getDataSourceIdentifier()).isPresent()){
                    continue;
                }
                String url = newsItem.getLink();

                //Avoid videos
                if (url.contains("video")){
                    continue;
                }
                try {
                    Document doc = Jsoup.connect(url)
                            .maxBodySize(0)  // 0 means unlimited size
                            .timeout(60000)  // set timeout to 60 seconds
                            .get();
                    // Extract all paragraphs from the article-body
                    Elements paragraphs = doc.select(".article-body p:not(.inline-photo full), .article-body p:not(.inline editorial float-r)");
                    StringBuilder newsBody = new StringBuilder();
                    for (Element paragraph : paragraphs) {
                        newsBody.append(paragraph.text()).append("\n\n");
                    }
                    String originalText = newsBody.toString().trim();
                    //Todo:
                    // Rewrite the text String rewrittenText = rewriteText(originalText);
                    newsItem.setNewsBody(originalText); // Set the rewritten text
                    jpaF1NewsAdapter.save(newsItem);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Optional<List<F1News>> fetchDriverNews() {
        try {
            // The info its extracted from ESPN
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://f1-motorsport-data.p.rapidapi.com/news?limit=50"))
                    .header("x-rapidapi-key", apiKey)
                    .header("x-rapidapi-host", "f1-motorsport-data.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            List<F1News> news = F1NewsMapper.fromJson(response.body());
            return Optional.of(news);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private String rewriteText(String text) {
        return text;
    }


}
