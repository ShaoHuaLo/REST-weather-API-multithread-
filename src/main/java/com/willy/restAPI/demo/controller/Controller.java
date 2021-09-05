package com.willy.restAPI.demo.controller;

import com.willy.restAPI.demo.BadRequestException;
import com.willy.restAPI.demo.dto.ConsolidatedWeather;
import com.willy.restAPI.demo.dto.SimpleWeatherData;
import com.willy.restAPI.demo.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired
    private WeatherService service;

    @GetMapping(path = "/location/search", params = "query")
    public SimpleWeatherData[] getLocationSearch_location(@RequestParam("query") String location) throws ExecutionException, InterruptedException {
        return service.getLocationSearch_location(location);
    }

    @GetMapping(path="/location/search", params="lattlong")
    public SimpleWeatherData[] getLocationSearch_latt(@RequestParam String lattlong) throws ExecutionException, InterruptedException {
        return service.getLocationSearch_latt(lattlong);
    }

    @GetMapping("/location/{woeid}")
    public SimpleWeatherData getLocation(@PathVariable int woeid) throws ExecutionException, InterruptedException {
        return service.getLocation(woeid);
    }

    @GetMapping("/location/{id}/{year}/{month}/{day}")
    public ConsolidatedWeather[] getLocationDay(@PathVariable int id, @PathVariable int year,
                                            @PathVariable int month, @PathVariable int day) throws Throwable{
        return service.getLocationDay(id, year, month, day);
    }

}
