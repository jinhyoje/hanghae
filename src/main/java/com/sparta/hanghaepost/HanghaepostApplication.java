package com.sparta.hanghaepost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HanghaepostApplication {

    public static void main(String[] args) {
        SpringApplication.run(HanghaepostApplication.class, args);
    }

}
