package com.consumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GreetingService {

    private static final Logger logger = LoggerFactory.getLogger(GreetingService.class);

    @Value("${greeting.api.url}")
    private String greetingAPI;

    @NonNull
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "defaultGreeting")
    public String getGreeting(String username) {
        logger.info("Request: Fetching user {} in greetings API.", username);
        final String userResponse = restTemplate.getForObject(this.greetingAPI, String.class, username);
        logger.info("Response: from greetings API: {}.", userResponse);
        return userResponse;

    }

    private String defaultGreeting(String username) {
        return "Hello, default user";
    }
}