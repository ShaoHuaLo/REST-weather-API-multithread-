package com.willy.restAPI.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiCallerImpl implements ApiCaller{
    private RestTemplate template;

    @Autowired
    public ApiCallerImpl(RestTemplate template) {
        this.template = template;
    }

    public String call(String url) {
        ResponseEntity<String> jsonString = template.getForEntity(url, String.class);
        return jsonString.getBody();
    }
}
