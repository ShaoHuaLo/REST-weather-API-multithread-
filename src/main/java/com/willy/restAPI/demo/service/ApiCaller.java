package com.willy.restAPI.demo.service;

import org.springframework.web.client.RestClientException;

public interface ApiCaller {
    String call(String url) throws RestClientException;
}
