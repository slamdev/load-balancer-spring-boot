package com.github.slamdev.boot.load.balancer.autoconfigure;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

import static java.util.Collections.emptyList;

@ConfigurationProperties(prefix = LoadBalancerProperties.PREFIX)
@Getter
@Setter
public class LoadBalancerProperties {

    public static final String PREFIX = "load-balancer";

    private List<String> hosts = emptyList();

    private long hostAvailabilityCheckDuration = 1;
}
