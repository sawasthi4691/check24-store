package com.sb.book.check24;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Check24StoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(Check24StoreApplication.class, args);
    }

}
