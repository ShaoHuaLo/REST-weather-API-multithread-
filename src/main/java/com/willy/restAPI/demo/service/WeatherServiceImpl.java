package com.willy.restAPI.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.willy.restAPI.demo.dto.ConsolidatedWeather;
import com.willy.restAPI.demo.dto.LocationDto;
import com.willy.restAPI.demo.dto.LocationSearchDto;
import com.willy.restAPI.demo.dto.SimpleWeatherDto;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executor;

@Service
public class WeatherServiceImpl implements WeatherService{
    private Executor executor;
    private ApiCaller caller;
    private ObjectMapper mapper;

    @Autowired
    public WeatherServiceImpl(Executor executor, ApiCaller caller, ObjectMapper mapper) {
        this.executor = executor;
        this.caller = caller;
        this.mapper = mapper;
    }

    @SneakyThrows
    public LocationSearchDto[] getLocationSearch_Location(String location) {
        String url = "https://www.metaweather.com/api/location/search/?query=" + location;
        String jsonStr = caller.call(url);
        return mapper.readValue(jsonStr, LocationSearchDto[].class);
    }

    @SneakyThrows
    public LocationSearchDto[] getLocationSearch_LattLong(String lattlong) {
        String url = "https://www.metaweather.com/api/location/search/?lattlong=" + lattlong;
        String jsonStr = caller.call(url);
        return mapper.readValue(jsonStr, LocationSearchDto[].class);
    }

    @SneakyThrows
    public LocationDto getLocationById(int id) {
        String url = "https://www.metaweather.com/api/location/" + id + "/";
        String jsonStr = caller.call(url);
        return mapper.readValue(jsonStr, LocationDto.class);
    }

    @SneakyThrows
    public ConsolidatedWeather[] getLocationDay(int id, int year, int month, int day) {
        String url = "https://www.metaweather.com/api/location/" + id + "/" + year + "/" + month + "/" + day + "/";
        String jsonStr = caller.call(url);
        return mapper.readValue(jsonStr, ConsolidatedWeather[].class);
    }

}
