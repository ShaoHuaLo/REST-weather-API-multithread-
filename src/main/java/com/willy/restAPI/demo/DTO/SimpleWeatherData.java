package com.willy.restAPI.demo.DTO;

import lombok.Data;

@Data
public class SimpleWeatherData {
    private String title;
    private String location_type;
    private String latt_long;
}
