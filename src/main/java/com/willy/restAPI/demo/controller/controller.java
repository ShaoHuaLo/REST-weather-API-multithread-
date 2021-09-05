package com.willy.restAPI.demo.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.willy.restAPI.demo.entity.SimpleWeatherData;
import com.willy.restAPI.demo.util.UtilMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@RestController
@RequestMapping("/api")
public class controller {
    @Autowired
    private Executor executor;
    @Autowired
    private RestTemplate template;

    @GetMapping(path = "/location/search", params = "query")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public SimpleWeatherData[] getLocationSearch_location(@RequestParam("query") String location) throws Throwable {
        String url = "https://www.metaweather.com/api/location/search/?query=" + location;
        CompletableFuture<ResponseEntity<SimpleWeatherData[]>> weatherFuture = CompletableFuture.supplyAsync(() -> {
            ResponseEntity<SimpleWeatherData[]> response = template.getForEntity(url, SimpleWeatherData[].class);
            return response;
        }, executor);
        // since Future.get() is blocking method and we don't want blocking
        // should we use callback (thenApply etc)?
        return weatherFuture.get().getBody();
    }

    @GetMapping(path="/location/search", params="lattlong")
    public SimpleWeatherData[] getLocationSearch_latt(@RequestParam String lattlong) throws Throwable {
        String url = "https://www.metaweather.com/api/location/search/?lattlong=" + lattlong;
        CompletableFuture<ResponseEntity<SimpleWeatherData[]>> weatherFuture = CompletableFuture.supplyAsync(() -> {
            ResponseEntity<SimpleWeatherData[]> response = template.getForEntity(url, SimpleWeatherData[].class);
            return response;
        }, executor);
        return weatherFuture.get().getBody();
    }

    @GetMapping("/location/{id}")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public SimpleWeatherData getLocation(@PathVariable int id) throws Throwable {
        String url = "https://www.metaweather.com/api/location/" + id + "/";
        CompletableFuture<ResponseEntity<SimpleWeatherData>> weatherFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getId() + " is working on location_id = " + id);
            ResponseEntity<SimpleWeatherData> response = template.getForEntity(url, SimpleWeatherData.class);
            return response;
        }, executor);

        // since Future.get() is blocking method and we don't want blocking
        // should we use callback (thenApply etc) ??

        return weatherFuture.get().getBody();
    }

}
