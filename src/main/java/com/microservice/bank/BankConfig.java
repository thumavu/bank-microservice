package com.microservice.bank;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BankConfig {

    @Value("${hub.url}")
    private String hubUrl;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public HubServiceClient hubServiceClient(RestTemplate restTemplate) {
        return new HubServiceClient(restTemplate, hubUrl);
    }
}