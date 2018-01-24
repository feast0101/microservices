package com.optum.springcloud.circuitbreaker;

import com.optum.springcloud.circuitbreaker.service.CircuitService;
import com.netflix.config.ConfigurationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@EnableCircuitBreaker
@EnableHystrixDashboard
@RestController
@SpringBootApplication
public class SpringCircuitbreakerApplication {

    @Autowired
    private CircuitService circuitService;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }


    @RequestMapping("/claimcircuitbreak")
    public String toClaimRead() {

        return circuitService.claimBreakerMethod();
    }
    @RequestMapping("/providercircuitbreak")
    public String toProviderRead() {

        return circuitService.providerBreakerMethod();
    }
    public static void main(String[] args) {

        SpringApplication.run(SpringCircuitbreakerApplication.class, args);

	}
}
