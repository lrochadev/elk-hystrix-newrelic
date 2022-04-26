package com.consumer.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
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


    @CircuitBreaker(name = "greeting-service", fallbackMethod = "defaultGreeting")
    @Retry(name = "greeting-service", fallbackMethod = "defaultGreeting")
    public String getGreeting(String username) {
        logger.info("Request: Fetching user {} in greetings API.", username);
        final String userResponse = restTemplate.getForObject(this.greetingAPI, String.class, username);
        logger.info("Response: from greetings API: {}.", userResponse);
        return userResponse;
    }

    private String defaultGreeting(Exception exception) {
        return String.format("Fallback Execution for Circuit Breaker. Error Message: %s", exception.getMessage());
    }
}