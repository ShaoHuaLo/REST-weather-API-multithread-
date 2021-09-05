package com.willy.restAPI.demo.controller;

import com.willy.restAPI.demo.DTO.ConsolidatedWeather;
import com.willy.restAPI.demo.DTO.SimpleWeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@RestController
@RequestMapping("/api")
public class controller {
    @Autowired
    private Executor executor;
    @Autowired
    private RestTemplate template;

    /**
     *
     * @param location : city / location to search for ex: london, san.
     * @return : json data
     * @throws Throwable : (interrupted exception)
     */
    @GetMapping(path = "/location/search", params = "query")
    public SimpleWeatherData[] getLocationSearch_location(@RequestParam("query") String location) throws Throwable {
        String url = "https://www.metaweather.com/api/location/search/?query=" + location;
        CompletableFuture<ResponseEntity<SimpleWeatherData[]>> weatherFuture = CompletableFuture.supplyAsync(() -> {
            ResponseEntity<SimpleWeatherData[]> response = template.getForEntity(url, SimpleWeatherData[].class);
            return response;
        }, executor);
        // since Future.get() is blocking method and we don't want blocking
        // should we use callback (thenApply etc)?
        return weatherFuture.get().getBody();
    }

    /**
     *
     * @param lattlong : two double representing lattitude and longitude of the query location seperated by comma.
     * @return : json data
     * @throws Throwable : interrupted exception
     */
    @GetMapping(path="/location/search", params="lattlong")
    public SimpleWeatherData[] getLocationSearch_latt(@RequestParam String lattlong) throws Throwable {
        String url = "https://www.metaweather.com/api/location/search/?lattlong=" + lattlong;
        CompletableFuture<ResponseEntity<SimpleWeatherData[]>> weatherFuture = CompletableFuture.supplyAsync(() -> {
            ResponseEntity<SimpleWeatherData[]> response = template.getForEntity(url, SimpleWeatherData[].class);
            return response;
        }, executor);
        return weatherFuture.get().getBody();
    }

    /**
     *
     * @param id : woeid represents Where-On-Earch id. For example, 44418 represents London, 2487956 represents San Frescisco
     * @return : json data
     * @throws Throwable : interrupted exception
     */
    @GetMapping("/location/{id}")
    public SimpleWeatherData getLocation(@PathVariable int id) throws Throwable {
        String url = "https://www.metaweather.com/api/location/" + id + "/";
        CompletableFuture<ResponseEntity<SimpleWeatherData>> weatherFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getId() + " is working on location_id = " + id);
            ResponseEntity<SimpleWeatherData> response = template.getForEntity(url, SimpleWeatherData.class);
            return response;
        }, executor);

        // since Future.get() is blocking method and we don't want blocking
        // should we use callback (thenApply etc) ??

        return weatherFuture.get().getBody();
    }

    @GetMapping("/location/{id}/{year}/{month}/{day}")
    public ConsolidatedWeather[] getLocationDay(@PathVariable int id, @PathVariable int year,
                                            @PathVariable int month, @PathVariable int day) throws Throwable{
        String url = "https://www.metaweather.com/api/location/" + id + "/" + year + "/" + month + "/" + day + "/";
        CompletableFuture<ResponseEntity<ConsolidatedWeather[]>> weatherFuture = CompletableFuture.supplyAsync(() -> {
                    ResponseEntity<ConsolidatedWeather[]> response = template.getForEntity(url, ConsolidatedWeather[].class);
                    return response;
        }, executor);

        return weatherFuture.get().getBody();
    }

}
