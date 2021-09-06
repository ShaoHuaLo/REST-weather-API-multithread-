package com.willy.restAPI.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.willy.restAPI.demo.dto.ConsolidatedWeather;
import com.willy.restAPI.demo.dto.LocationDto;
import com.willy.restAPI.demo.dto.LocationSearchDto;
import com.willy.restAPI.demo.exceptionhandler.exception.BadInputException;
import com.willy.restAPI.demo.exceptionhandler.exception.ResultNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

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

    public LocationSearchDto[] getLocationSearch_Location(String location) throws JsonProcessingException {
        if (location == null || location.isEmpty())
            throw new BadInputException();

        String url = "https://www.metaweather.com/api/location/search/?query=" + location;
        String jsonStr = null;
        try {
            jsonStr = caller.call(url);
        } catch (RestClientException e) {
            throw e;
        }
        if (jsonStr == null || jsonStr.isEmpty())
            throw new ResultNotFoundException("result not found with input location = " + location);

        LocationSearchDto[] locationSearchDtos = mapper.readValue(jsonStr, LocationSearchDto[].class);

        if (locationSearchDtos.length == 0)
            throw new ResultNotFoundException("result not found with input location = " + location);

        return locationSearchDtos;
    }

    public LocationSearchDto[] getLocationSearch_LattLong(String lattlong) throws JsonProcessingException {
        if (lattlong == null || lattlong.isEmpty() || lattlong.split(",").length != 2)
            throw new BadInputException();

        String url = "https://www.metaweather.com/api/location/search/?lattlong=" + lattlong;
        String jsonStr = null;
        try {
            jsonStr = caller.call(url);
        } catch (RestClientException e) {
            throw e;
        }

        if (jsonStr == null || jsonStr.isEmpty())
            throw new ResultNotFoundException("result not found with input lattlong = " + lattlong);

        LocationSearchDto[] locationSearchDtos = mapper.readValue(jsonStr, LocationSearchDto[].class);

        if (locationSearchDtos.length == 0) {
            throw new ResultNotFoundException("result not found with input lattlong = " + lattlong);
        }
        return locationSearchDtos;
    }

    public LocationDto getLocationById(int id) throws JsonProcessingException {
        if (id < 0)
            throw new BadInputException();
        String url = "https://www.metaweather.com/api/location/" + id + "/";
        String jsonStr = null;
        try {
            jsonStr = caller.call(url);
        } catch (RestClientException e) {
            throw e;
        }
        if (jsonStr == null || jsonStr.isEmpty())
            throw new ResultNotFoundException("result not found with input id = " + id);
        LocationDto locationDto = mapper.readValue(jsonStr, LocationDto.class);
        return locationDto;
    }

    public ConsolidatedWeather[] getLocationDay(int id, int year, int month, int day) throws JsonProcessingException {
        if (id < 0 || year < 0 || day < 0 || month < 0 || month > 12 || day > 31)
            throw new BadInputException();
        String url = "https://www.metaweather.com/api/location/" + id + "/" + year + "/" + month + "/" + day + "/";
        String jsonStr = null;
        try {
            jsonStr = caller.call(url);
        } catch (RestClientException e) {
            throw e;
        }

        if (jsonStr == null || jsonStr.isEmpty())
            throw new ResultNotFoundException("result not found with input id=" + id +
                    ", year=" + year + ", month=" + month + ", day=" + day);

        ConsolidatedWeather[] consolidatedWeathers = mapper.readValue(jsonStr, ConsolidatedWeather[].class);

        if (consolidatedWeathers.length == 0)
            throw new ResultNotFoundException("result not found with input id=" + id +
                    ", year=" + year + ", month=" + month + ", day=" + day);
        return consolidatedWeathers;
    }

}
