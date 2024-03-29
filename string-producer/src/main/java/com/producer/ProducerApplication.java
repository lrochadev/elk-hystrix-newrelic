package com.producer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RequestMapping("/greetings")
@RestController
@EnableEurekaClient
@Tag(name = "Endpoints de boas-vindas ao usuário.")
public class ProducerApplication {

    private static final Logger logger = LoggerFactory.getLogger(ProducerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

    @Operation(summary = "Retorna mensagem de boas-vindas ao usuário.")
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "Ok"))
    @GetMapping("/{username}")
    public ResponseEntity<String> greeting(@PathVariable("username") String username) {
        final String response = "Hello, " + username;
        logger.info(response);
        return ResponseEntity.ok(response);
    }
}
