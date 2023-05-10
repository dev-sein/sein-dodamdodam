package com.app.dodamdodam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DodamdodamApplication {

    public static void main(String[] args) {
        SpringApplication.run(DodamdodamApplication.class, args);
    }

}
