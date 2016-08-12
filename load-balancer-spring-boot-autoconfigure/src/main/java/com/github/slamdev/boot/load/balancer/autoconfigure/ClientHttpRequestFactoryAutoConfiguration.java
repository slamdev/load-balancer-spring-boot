package com.github.slamdev.boot.load.balancer.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

@Configuration
public class ClientHttpRequestFactoryAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    private ClientHttpRequestFactory factory() {
        return new SimpleClientHttpRequestFactory();
    }
}
