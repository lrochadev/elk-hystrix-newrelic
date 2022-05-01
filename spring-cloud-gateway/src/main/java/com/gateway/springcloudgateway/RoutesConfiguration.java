package com.gateway.springcloudgateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutesConfiguration {

    @Value("${consumer.api.url}")
    private String consumerAPI;

    @Value("${producer.api.url}")
    private String producerAPI;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/consumer/**")
                        .filters(f -> f.retry(5))
                        .uri(consumerAPI)
                )
                .route(r -> r.path("/producer/**")
                        .filters(f -> f.addResponseHeader("X-AnotherHeader", "passou_no_gtw"))
                        .uri(producerAPI)
                )
                .build();
    }

}
