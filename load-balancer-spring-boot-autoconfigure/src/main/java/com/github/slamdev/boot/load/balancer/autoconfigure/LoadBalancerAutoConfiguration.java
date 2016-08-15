package com.github.slamdev.boot.load.balancer.autoconfigure;

import com.github.slamdev.load.balancer.HostAvailabilityChecker;
import com.github.slamdev.load.balancer.LoadBalancer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import java.io.IOException;

import static java.net.URI.create;
import static java.time.Duration.of;
import static java.time.temporal.ChronoUnit.SECONDS;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.OK;

@Configuration
@ConditionalOnClass(LoadBalancer.class)
@EnableConfigurationProperties(LoadBalancerProperties.class)
public class LoadBalancerAutoConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoadBalancerAutoConfiguration.class);

    @Autowired
    private LoadBalancerProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public LoadBalancer loadBalancer() {
        return new LoadBalancer(
                properties.getHosts(),
                hostAvailabilityChecker(),
                of(properties.getHostAvailabilityCheckDuration(), SECONDS)
        );
    }

    private HostAvailabilityChecker hostAvailabilityChecker() {
        return (url) -> {
            try {
                ClientHttpRequest request = new SimpleClientHttpRequestFactory().createRequest(create(url + "/health"), GET);
                ClientHttpResponse response = request.execute();
                return response.getStatusCode() == OK;
            } catch (IOException e) {
                LOGGER.error("Failed to check host " + url, e);
            }
            return false;
        };
    }
}
