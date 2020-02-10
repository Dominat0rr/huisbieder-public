package com.domain.fednot_demo_huisbieder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class FednotDemoHuisbiederApplication {

    public static void main(String[] args) {
        SpringApplication.run(FednotDemoHuisbiederApplication.class, args);
    }

}
