package com.willy.restAPI.demo.dto;

import lombok.Data;

@Data
public class LocationDto {
    private String title;
    private String location_type;
    private int woeid;
    private String latt_long;
    private String timezone;
    private String time;
    private String sun_rise;
    private String sun_set;
    private String timezone_name;
    private LocationSearchDto parent;
    private ConsolidatedWeather[] consolidated_weather;
}
