package com.willy.restAPI.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ApiConfig {

    @Bean
    public Executor executor() {
        System.out.println("executor is created!!!");
        return Executors.newFixedThreadPool(5);
    }

    @Bean
    public RestTemplate restTemplate() {
        System.out.println("template is created!!!");
        return new RestTemplate();
    }
}
