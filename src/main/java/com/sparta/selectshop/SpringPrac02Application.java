package com.sparta.selectshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableJpaAuditing
@SpringBootApplication
public class SpringPrac02Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringPrac02Application.class, args);
    }

}
