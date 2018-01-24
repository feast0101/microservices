package com.optum.springcloud.circuitbreaker.service;

import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * @author Kallol
 */

@RefreshScope
@Service
public class CircuitService {

    private final RestTemplate restTemplate;

    public CircuitService(RestTemplate rest) {
        this.restTemplate = rest;
    }

    //This value is populated from CodeHub bar.properties file
    //Manual refresh - POST - http://localhost:8060/refresh
    @Value("${hystrix.command.default.fallback.enabled:true}")
    private boolean fallbackEnabled;

    //This value is populated from CodeHub bar.properties file
    //Manual refresh - POST - http://localhost:8060/refresh
    @Value("${hystrix.command.default.circuitBreaker.sleepWindowInMilliseconds:5000}")
    private String hystrixSleep;

    //This value is populated from CodeHub bar.properties file
    //Manual refresh - POST - http://localhost:8060/refresh
    @Value("${hystrix.command.default.circuitBreaker.requestVolumeThreshold:20}")
    private String volumeThreshold;

    @HystrixCommand(fallbackMethod = "claimBackup")
    public String claimBreakerMethod() {

        System.out.println("Configuration - hystrix.command.default.fallback.enabled: " + fallbackEnabled);
        System.out.println("Configuration - hystrix.command.default.circuitBreaker.sleepWindowInMilliseconds: " + hystrixSleep);
        System.out.println("Configuration - hystrix.command.default.circuitBreaker.requestVolumeThreshold: " + volumeThreshold);

        //Configuration coming from codehub - Spring Cloud Config Server
        //CM Details - https://github.com/Netflix/Hystrix/wiki/Configuration
        ConfigurationManager.getConfigInstance().setProperty("hystrix.command.default.fallback.enabled", fallbackEnabled);
        ConfigurationManager.getConfigInstance().setProperty("hystrix.command.default.circuitBreaker.sleepWindowInMilliseconds", hystrixSleep);
        ConfigurationManager.getConfigInstance().setProperty("hystrix.command.default.circuitBreaker.requestVolumeThreshold", volumeThreshold);

        //This code block is to trigger a circuit breaker test intermittently
        if( Math.random() > .5) {
            throw new RuntimeException("Breaker Exception");
        }

        //Calling the microserice
        URI uri = URI.create("http://localhost:2222/claim");
        return this.restTemplate.getForObject(uri, String.class);
    }
    @HystrixCommand(fallbackMethod = "providerBackup")
    public String providerBreakerMethod() {

        System.out.println("Configuration - hystrix.command.default.fallback.enabled: " + fallbackEnabled);
        System.out.println("Configuration - hystrix.command.default.circuitBreaker.sleepWindowInMilliseconds: " + hystrixSleep);
        System.out.println("Configuration - hystrix.command.default.circuitBreaker.requestVolumeThreshold: " + volumeThreshold);

        //Configuration coming from codehub - Spring Cloud Config Server
        //CM Details - https://github.com/Netflix/Hystrix/wiki/Configuration
        ConfigurationManager.getConfigInstance().setProperty("hystrix.command.default.fallback.enabled", fallbackEnabled);
        ConfigurationManager.getConfigInstance().setProperty("hystrix.command.default.circuitBreaker.sleepWindowInMilliseconds", hystrixSleep);
        ConfigurationManager.getConfigInstance().setProperty("hystrix.command.default.circuitBreaker.requestVolumeThreshold", volumeThreshold);

        //This code block is to trigger a circuit breaker test intermittently
        if( Math.random() > .5) {
            throw new RuntimeException("Breaker Exception");
        }

        //Calling the microserice
        URI uri = URI.create("http://localhost:3333/provider");
        return this.restTemplate.getForObject(uri, String.class);
    }
    public String claimBackup() {
        URI uri = URI.create("http://localhost:3333/provider");
        return this.restTemplate.getForObject(uri, String.class);
        //return "You reached the fallback service response - Circuit Breaker working!";
    }
    public String providerBackup() {
        URI uri = URI.create("http://localhost:2222/claim");
        return this.restTemplate.getForObject(uri, String.class);
        //return "You reached the fallback service response - Circuit Breaker working!";
    }
}
