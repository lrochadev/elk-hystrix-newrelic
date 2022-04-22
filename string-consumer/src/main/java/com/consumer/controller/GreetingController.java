package com.consumer.controller;

import com.consumer.service.GreetingService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/v1/get-greetings")
@RequiredArgsConstructor
public class GreetingController {

    @NonNull
    private final GreetingService greetingService;

    @GetMapping("/{username}")
    public ResponseEntity<?> getGreeting(@PathVariable("username") String username) {
        return ResponseEntity.ok(Map.of("message", greetingService.getGreeting(username)));
    }
}