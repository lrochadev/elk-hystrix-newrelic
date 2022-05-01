package com.gateway.springcloudgateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutesConfiguration {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/consumer/**")
                        .filters(f -> f.retry(5))
                        .uri("lb://string-consumer")
                )
                .route(r -> r.path("/producer/**")
                        .filters(f -> f.addResponseHeader("X-AnotherHeader", "passou_no_gtw"))
                        .uri("lb://string-producer")
                )
                .build();
    }

}
