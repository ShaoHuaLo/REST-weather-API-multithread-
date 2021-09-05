package com.willy.restAPI.demo.dto;

import lombok.Data;

@Data
public class ConsolidatedWeather {
    private long id;
    private String weather_state_name;
    private String wind_direction_compass;
    private String created;
    private String applicable_date;
    private double min_temp;
    private double max_temp;
    private double the_temp;
    private double wind_speed;
    private double wind_direction;
    private double air_pressure;
    private double humidity;
    private double visibility;
    private double predictability;
}
