package com.microservice.bank;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * BankConfig is a config class is responsible for calling the HubServiceClient for communication
 * with the Hub.
 * It is also responsible for making HTTP calls.
 * */
@Configuration
public class BankConfig {

    // Injecting the url from the applications.properties file
    @Value("${hub.url}")
    private String hubUrl;

    /**
     * Standard restTemplate is responsible for making HTTP calls. It helps with web APIs.
     * A new instance is created for better management of request/response life-cycles.
     * */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * The HubServiceClient is responsible for communicating with the Hub.
     * Everytime the bean is injected, a new instance is created. It helps with managing statefulness of the object.
     *
     * */
    @Bean
    public HubServiceClient hubServiceClient(RestTemplate restTemplate) {
        return new HubServiceClient(restTemplate, hubUrl);
    }
}