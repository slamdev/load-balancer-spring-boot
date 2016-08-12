package com.github.slamdev.boot.load.balancer.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        restTemplate.getForEntity("/index.html", String.class);
    }
}
