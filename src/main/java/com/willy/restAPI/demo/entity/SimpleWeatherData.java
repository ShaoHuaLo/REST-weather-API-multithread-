package com.willy.restAPI.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SimpleWeatherData {
    private String title;
    private String location_type;
    private String latt_long;
}
