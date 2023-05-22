package com.microservice.bank;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class HubServiceClient {

    private final RestTemplate restTemplate;
    private final String hubUrl;

    public HubServiceClient(RestTemplate restTemplate, @Value(value = "${hub.url}") String hubUrl) {
        this.restTemplate = new RestTemplate();
        this.hubUrl = hubUrl;
    }

    public void sendFileToBank(String bankId, Bank.FileData fileData) {
        String url = hubUrl + "/hub/send-file/" + bankId;

        try {
            restTemplate.postForObject(url, fileData, ResponseEntity.class);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
    }
}