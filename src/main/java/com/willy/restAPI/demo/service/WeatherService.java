package com.willy.restAPI.demo.service;

import com.willy.restAPI.demo.dto.ConsolidatedWeather;
import com.willy.restAPI.demo.dto.LocationDto;
import com.willy.restAPI.demo.dto.LocationSearchDto;
import com.willy.restAPI.demo.dto.SimpleWeatherDto;


public interface WeatherService {
    LocationSearchDto[] getLocationSearch_Location(String location);
    LocationSearchDto[] getLocationSearch_LattLong(String lattlong);
    LocationDto getLocationById(int id);
    ConsolidatedWeather[] getLocationDay(int id, int year, int month, int day);
}
