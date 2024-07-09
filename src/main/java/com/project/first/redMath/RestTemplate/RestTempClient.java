package com.project.first.redMath.RestTemplate;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class RestTempClient {

    private final RestTemplate restTemplate;

    public RestTempClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Scheduled(fixedRate = 6000)
    @Cacheable(cacheNames = "news")
    public List<News> findNews() {
        String result = restTemplate.getForEntity("https://newsapi.org/v2/top-headlines/sources?apiKey=ceeb61cc970f4a08bc6a85110fdd2ced&category=technology", String.class).getBody();
        News news = new News();
        news.setDescription(result);
        List<News> newsList = List.of(news);
        System.out.println("Cached News Result:");
        newsList.forEach(n -> System.out.println(n.getDescription()));
        return newsList;
    }

}
