package com.consumer.controller;

import com.consumer.service.GreetingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/get-greetings")
@RequiredArgsConstructor
@Tag(name = "Endpoints de consulta do usuário.")
public class GreetingController {

    private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);

    @NonNull
    private final GreetingService greetingService;

    @Operation(summary = "Busca usuário por nome na API de boas-vindas")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Ok"), @ApiResponse(responseCode = "500", description = "Internal server error")})
    @GetMapping("/{username}")
    public ResponseEntity<?> getGreeting(@PathVariable("username") String username) {
        return ResponseEntity.ok(Map.of("message", greetingService.getGreeting(username)));
    }

    @GetMapping(path = "/ip")
    public ResponseEntity<InetAddress> getIp() {
        try {
            return ResponseEntity.ok(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            logger.error(e.getMessage());
        }
        return ResponseEntity.internalServerError().build();
    }
}