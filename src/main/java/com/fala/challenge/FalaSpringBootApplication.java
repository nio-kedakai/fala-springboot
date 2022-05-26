package com.fala.challenge;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@ConditionalOnMissingBean({Flyway.class})
@EnableJpaRepositories(basePackages = {"com.fala.challenge"})
@EntityScan(basePackages = {"com.fala.challenge"})
@SpringBootApplication(scanBasePackages = {"com.fala.challenge"})
public class FalaSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(FalaSpringBootApplication.class, args);
    }

}
