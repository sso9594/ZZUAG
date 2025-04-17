package com.recycle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZzuagExternalApiApplication {
    public static void main(String[] args) {
        System.setProperty("spring.config.location", "classpath:/domain-property/application-cache-prod.yml,classpath:/");
//        System.setProperty("spring.config.location", "classpath:/domain-property/application-cache-local.yml,classpath:/");

        SpringApplication.run(ZzuagExternalApiApplication.class, args);
    }
}
