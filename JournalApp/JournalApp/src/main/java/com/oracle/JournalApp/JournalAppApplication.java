package com.oracle.JournalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication

public class JournalAppApplication {

    public static void main(String[] args) {

        SpringApplication.run(JournalAppApplication.class, args);

    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
