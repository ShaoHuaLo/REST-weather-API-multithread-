package com.willy.restAPI.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocationSearchDto {
    private String title;
    private String location_type;
    private String latt_long;
    private Integer woeid;
    // distance is possibly null since only location search by lattlong will non null
    private Integer distance;
}
