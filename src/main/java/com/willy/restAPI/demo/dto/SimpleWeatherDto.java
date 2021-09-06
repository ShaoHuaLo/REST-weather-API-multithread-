package com.willy.restAPI.demo.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SimpleWeatherDto {
    private String title;
    private String locationType;
    private String coordinate;
    private int woeid;

    @JsonCreator
    public SimpleWeatherDto(
            @JsonProperty("title") String title,
            @JsonProperty("location_type") String locationType,
            @JsonProperty("latt_long") String coordinate,
            @JsonProperty("woeid") int woeid) {
        System.out.println("full args");
        this.title = title;
        this.locationType = locationType;
        this.coordinate = coordinate;
        this.woeid = woeid;
    }
}
