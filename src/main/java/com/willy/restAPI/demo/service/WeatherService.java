package com.willy.restAPI.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.willy.restAPI.demo.dto.ConsolidatedWeather;
import com.willy.restAPI.demo.dto.LocationDto;
import com.willy.restAPI.demo.dto.LocationSearchDto;
import com.willy.restAPI.demo.dto.SimpleWeatherDto;


public interface WeatherService {
    LocationSearchDto[] getLocationSearch_Location(String location) throws JsonProcessingException;
    LocationSearchDto[] getLocationSearch_LattLong(String lattlong) throws JsonProcessingException;
    LocationDto getLocationById(int id) throws JsonProcessingException;
    ConsolidatedWeather[] getLocationDay(int id, int year, int month, int day) throws JsonProcessingException;
}
