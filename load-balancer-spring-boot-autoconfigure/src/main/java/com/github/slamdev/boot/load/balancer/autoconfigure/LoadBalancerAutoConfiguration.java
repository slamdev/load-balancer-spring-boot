package com.github.slamdev.boot.load.balancer.autoconfigure;

import com.github.slamdev.load.balancer.LoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.github.slamdev.boot.load.balancer.autoconfigure.LoadBalancerProperties.PREFIX;
import static java.time.Duration.of;
import static java.time.temporal.ChronoUnit.SECONDS;
import static org.springframework.util.Assert.notEmpty;

@Configuration
@ConditionalOnClass(LoadBalancer.class)
@EnableConfigurationProperties(LoadBalancerProperties.class)
public class LoadBalancerAutoConfiguration {

    @Autowired
    private LoadBalancerProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public LoadBalancer loadBalancer() {
        notEmpty(properties.getHosts(), "LoadBalancer properties not configured properly. Please check " + PREFIX
                + ".* properties settings in configuration file.");
        return new LoadBalancer(
                properties.getHosts(),
                null,
                of(properties.getHostAvailabilityCheckDuration(), SECONDS)
        );
    }
}
