
package com.example.configclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@SpringBootApplication
public class Application {

    private static final String template = "Hello, %s!";

    @Autowired
    private Environment environment;

    @Value("${foo2:test}")
    private String name;

    @Value("${spring.cloud.config.uri:http://localhost:8888}")
    private String cloudURI;

    @RequestMapping("/")
    public String query(@RequestParam("q") String q) {

        return environment.getProperty(q);
    }

    @RequestMapping("/home")
    public String home() {
        return String.format(template, name + ' ' + cloudURI);
    }

	public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
	}

}


