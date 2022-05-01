package com.consumer.infrastructure;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class RestTemplateConfiguration {

    @NonNull
    private final Environment environment;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(clientHttpRequestFactory());
    }

    private ClientHttpRequestFactory clientHttpRequestFactory() {

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        RequestConfig requestConfig = RequestConfig
                .custom()
                .setConnectionRequestTimeout(environment.getProperty("restTemplate.pool.connectionRequestTimeout", Integer.class, 0))
                .setConnectTimeout(environment.getProperty("restTemplate.pool.connectionTimeout", Integer.class, 0))
                .setSocketTimeout(environment.getProperty("restTemplate.pool.socketTimeout", Integer.class, 0))
                .build();

        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(environment.getProperty("restTemplate.pool.maxConnections", Integer.class, 0));
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(environment.getProperty("restTemplate.pool.maxPerRoute", Integer.class, 0));
        poolingHttpClientConnectionManager.setValidateAfterInactivity(environment.getProperty("restTemplate.pool.validateAfterInactivity", Integer.class, 0));

        CloseableHttpClient httpClientBuilder = HttpClientBuilder
                .create()
                .setConnectionManager(poolingHttpClientConnectionManager)
                .setDefaultRequestConfig(requestConfig)
                .build();

        requestFactory.setHttpClient(httpClientBuilder);

        return new BufferingClientHttpRequestFactory(requestFactory);
    }
}
