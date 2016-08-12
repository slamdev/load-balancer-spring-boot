package com.github.slamdev.boot.load.balancer.autoconfigure;

import com.github.slamdev.load.balancer.LoadBalancer;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadBalancerDisposer implements DisposableBean {

    @Autowired
    private LoadBalancer loadBalancer;

    @Override
    public void destroy() throws Exception {
        loadBalancer.dispose();
    }
}
