package com.mutsasnskimnayeong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MutsaSnsKimnayeongApplication {

    public static void main(String[] args) {
        SpringApplication.run(MutsaSnsKimnayeongApplication.class, args);
    }

}
