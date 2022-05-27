package com.fala.challenge;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories(basePackages = {"com.fala.challenge"})
@EntityScan(basePackages = {"com.fala.challenge"})
@SpringBootApplication(scanBasePackages = {"com.fala.challenge"})
public class FalaSpringBootApplication {


    public static void main(String[] args) {
        SpringApplication.run(FalaSpringBootApplication.class, args);
    }

//    @Bean
//    public Scheduler jdbcScheduler() {
//        return Schedulers.fromExecutor(Executors.newFixedThreadPool(100));
//    }


}
