package com.willy.restAPI.demo.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.willy.restAPI.demo.entity.SimpleWeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@RestController
@RequestMapping("/api")
public class controller {
    @Autowired
    private Executor executor;
    @Autowired
    private RestTemplate template;

    @GetMapping("/location/{id}")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public SimpleWeatherData getLondon(@PathVariable int id) throws Throwable {

        String londonUrl = "https://www.metaweather.com/api/location/" + id + "/";
        CompletableFuture<ResponseEntity<SimpleWeatherData>> weatherFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getId() + " is working on location_id = " + id);
            ResponseEntity<SimpleWeatherData> response = template.getForEntity(londonUrl, SimpleWeatherData.class);
            return response;
        }, executor);

        // since Future.get() is blocking method
        // should use callback (thenApply etc) or while-loop (!isDone()) ???
        while (!weatherFuture.isDone()) {

        }
        return weatherFuture.get().getBody();
    }

}
