package com.recycle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZzuagExternalQueryApiApplication {
    public static void main(String[] args) {
        System.setProperty("spring.config.location", "classpath:/domain-property/application-rds-prod.yml,classpath:/domain-property/application-redis-prod.yml,classpath:/application-prod.yml");
        SpringApplication.run(ZzuagExternalQueryApiApplication.class, args);
    }
}