package com.github.slamdev.boot.load.balancer.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class LoadBalancerRestTemplateCustomizer implements RestTemplateCustomizer {

    @Autowired
    @LoadBalanced
    private ClientHttpRequestFactory factory;

    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.setRequestFactory(factory);
    }
}
