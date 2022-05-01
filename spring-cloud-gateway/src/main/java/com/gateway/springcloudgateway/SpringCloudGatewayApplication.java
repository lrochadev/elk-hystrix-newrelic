package com.gateway.springcloudgateway;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class SpringCloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGatewayApplication.class, args);
    }

    @Component
    class DiscoveryClientExample implements CommandLineRunner {

        private final DiscoveryClient discoveryClient;

        public DiscoveryClientExample(DiscoveryClient discoveryClient) {
            this.discoveryClient = discoveryClient;
        }

        @Override
        public void run(String... strings) {
            discoveryClient.getInstances("STRING-CONSUMER").forEach((ServiceInstance s) -> System.out.println(ToStringBuilder.reflectionToString(s)));
            discoveryClient.getInstances("STRING-PRODUCER").forEach((ServiceInstance s) -> System.out.println(ToStringBuilder.reflectionToString(s)));
        }
    }
}

