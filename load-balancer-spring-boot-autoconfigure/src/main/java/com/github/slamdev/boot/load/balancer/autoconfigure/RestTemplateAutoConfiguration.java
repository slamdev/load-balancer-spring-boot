package com.github.slamdev.boot.load.balancer.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateAutoConfiguration {

    @Autowired
    private RestTemplateCustomizer customizer;

    @Bean
    @ConditionalOnMissingBean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().customizers(customizer).build();
    }
}
