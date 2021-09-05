package com.willy.restAPI.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.willy.restAPI.demo.dto.ConsolidatedWeather;
import com.willy.restAPI.demo.dto.SimpleWeatherData;
import com.willy.restAPI.demo.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class WeatherAPIController {
    @Autowired
    private WeatherService service;

    @GetMapping(path = "/location/search", params = "query")
    public SimpleWeatherData[] getLocationSearch_location(@RequestParam("query") String location) throws JsonProcessingException {
        return service.getLocationSearch_Location(location);
    }

    @GetMapping(path="/location/search", params="lattlong")
    public SimpleWeatherData[] getLocationSearch_latt(@RequestParam String lattlong) throws JsonProcessingException {
        return service.getLocationSearch_LattLong(lattlong);
    }

    @GetMapping("/location/{woeid}")
    public SimpleWeatherData getLocation(@PathVariable int woeid) throws ExecutionException, InterruptedException, IOException {
        return service.getLocationById(woeid);
    }

    @GetMapping("/location/{id}/{year}/{month}/{day}")
    public ConsolidatedWeather[] getLocationDay(@PathVariable int id, @PathVariable int year,
                                            @PathVariable int month, @PathVariable int day) throws Throwable{
        return service.getLocationDay(id, year, month, day);
    }

}
