package com.willy.restAPI.demo.dto;

import lombok.Data;

@Data
public class SimpleWeatherData {
    private String title;
    private String location_type;
    private String latt_long;
}
