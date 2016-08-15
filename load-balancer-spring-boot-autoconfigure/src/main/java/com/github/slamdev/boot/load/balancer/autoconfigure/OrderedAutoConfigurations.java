package com.github.slamdev.boot.load.balancer.autoconfigure;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;

@ImportAutoConfiguration({
        RestTemplateAutoConfiguration.class,
        LoadBalancerRestTemplateCustomizer.class,
        LoadBalancerClientHttpRequestFactory.class,
        LoadBalancerAutoConfiguration.class,
        LoadBalancerDisposer.class
})
public class OrderedAutoConfigurations {
}
