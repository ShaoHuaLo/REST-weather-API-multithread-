package com.willy.restAPI.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.willy.restAPI.demo.dto.ConsolidatedWeather;
import com.willy.restAPI.demo.dto.LocationDto;
import com.willy.restAPI.demo.dto.LocationSearchDto;
import com.willy.restAPI.demo.exceptionhandler.exception.BadInputException;
import com.willy.restAPI.demo.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class WeatherAPIController {
    private WeatherService service;

    @Autowired
    public WeatherAPIController(WeatherService service) {
        this.service = service;
    }

    @GetMapping(path = "/location/search", params = "query")
    public LocationSearchDto[] getLocationSearch_location(@RequestParam("query") String location) throws JsonProcessingException {
        return service.getLocationSearch_Location(location);
    }

    @GetMapping(path="/location/search", params="lattlong")
    public LocationSearchDto[] getLocationSearch_latt(@RequestParam String lattlong) throws JsonProcessingException {
        return service.getLocationSearch_LattLong(lattlong);
    }

    @GetMapping("/location/{woeid}")
    public LocationDto getLocation(@PathVariable int woeid) throws JsonProcessingException {
        return service.getLocationById(woeid);
    }

    @GetMapping("/location/{id}/{year}/{month}/{day}")
    public ConsolidatedWeather[] getLocationDay(@PathVariable int id, @PathVariable int year,
                                            @PathVariable int month, @PathVariable int day) throws JsonProcessingException {
        return service.getLocationDay(id, year, month, day);
    }

}
