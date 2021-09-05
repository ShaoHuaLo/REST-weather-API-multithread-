package com.willy.restAPI.demo.service;

import com.willy.restAPI.demo.dto.ConsolidatedWeather;
import com.willy.restAPI.demo.dto.SimpleWeatherData;
import org.springframework.stereotype.Service;


public interface WeatherService {
    SimpleWeatherData[] getLocationSearch_Location(String location);
    SimpleWeatherData[] getLocationSearch_LattLong(String lattlong);
    SimpleWeatherData getLocationById(int id);
    ConsolidatedWeather[] getLocationDay(int id, int year, int month, int day);
}
