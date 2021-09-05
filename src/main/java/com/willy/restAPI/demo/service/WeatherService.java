package com.willy.restAPI.demo.service;

import com.willy.restAPI.demo.BadRequestException;
import com.willy.restAPI.demo.dto.ConsolidatedWeather;
import com.willy.restAPI.demo.dto.SimpleWeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

@Service
public class WeatherService {
    @Autowired
    private Executor executor;
    @Autowired
    private RestTemplate template;


    public SimpleWeatherData[] getLocationSearch_location(String location) throws ExecutionException, InterruptedException {
        String url = "https://www.metaweather.com/api/location/search/?query=" + location;
        CompletableFuture<ResponseEntity<SimpleWeatherData[]>> weatherFuture = CompletableFuture.supplyAsync(() -> {
            ResponseEntity<SimpleWeatherData[]> response = template.getForEntity(url, SimpleWeatherData[].class);
            return response;
        }, executor);

        // since Future.get() is blocking method and we don't want blocking
        // should we use callback (thenApply etc)?
        return weatherFuture.get().getBody();
    }

    public SimpleWeatherData[] getLocationSearch_latt(String lattlong) throws ExecutionException, InterruptedException {
        String url = "https://www.metaweather.com/api/location/search/?lattlong=" + lattlong;
        CompletableFuture<ResponseEntity<SimpleWeatherData[]>> weatherFuture = CompletableFuture.supplyAsync(() -> {
            ResponseEntity<SimpleWeatherData[]> response = template.getForEntity(url, SimpleWeatherData[].class);
            return response;
        }, executor);
        return weatherFuture.get().getBody();
    }

    public SimpleWeatherData getLocation(int id) throws ExecutionException, InterruptedException {
        String url = "https://www.metaweather.com/api/location/" + id + "/";
        CompletableFuture<ResponseEntity<SimpleWeatherData>> weatherFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getId() + " is working on location_id = " + id);
            ResponseEntity<SimpleWeatherData> response = template.getForEntity(url, SimpleWeatherData.class);
            return response;
        }, executor);

        return weatherFuture.get().getBody();
    }

    public ConsolidatedWeather[] getLocationDay(int id, int year, int month, int day) throws ExecutionException, InterruptedException, BadRequestException {
        String url = "https://www.metaweather.com/api/location/" + id + "/" + year + "/" + month + "/" + day + "/";
//        AtomicBoolean exception_flag = new AtomicBoolean(false);
        CompletableFuture<ResponseEntity<ConsolidatedWeather[]>> weatherFuture = CompletableFuture.supplyAsync(() -> {
                    ResponseEntity<ConsolidatedWeather[]> response = template.getForEntity(url, ConsolidatedWeather[].class);
                    return response;
                }, executor)
                .exceptionally((ex) -> {
                    throw new BadRequestException("ooops... we ran into issues!!!" + ex.getMessage());
                });

        return weatherFuture.get().getBody();
    }




}
