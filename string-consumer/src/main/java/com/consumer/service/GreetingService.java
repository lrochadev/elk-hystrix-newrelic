package com.consumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class GreetingService {

    @Value("${greeting.api.url}")
    private String greetingAPI;

    @NonNull
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "defaultGreeting")
    public String getGreeting(String username) {
        return restTemplate.getForObject(this.greetingAPI, String.class, username);
    }

    private String defaultGreeting(String username) {
        return "Hello, Default User!";
    }
}