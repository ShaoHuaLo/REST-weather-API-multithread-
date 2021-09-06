package com.willy.restAPI.demo.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

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
