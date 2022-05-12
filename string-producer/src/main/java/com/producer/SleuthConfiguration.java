package com.producer;

import brave.sampler.Sampler;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SleuthConfiguration {

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }
}
