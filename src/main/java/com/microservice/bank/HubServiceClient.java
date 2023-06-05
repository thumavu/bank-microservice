package com.microservice.bank;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * The HubServiceClient acts as a client for the Hub microservices.
 * */
@Component
public class HubServiceClient {

    private final RestTemplate restTemplate;
    private final String hubUrl;

    /**
     * The constructor takes in the restTemplate and the Hub's URL.
     * The restTemplate is used for making HTTP requests.
     * the URL is pointing at the Hub's microservices.
     * */
    public HubServiceClient(RestTemplate restTemplate, @Value(value = "${hub.url}") String hubUrl) {
        this.restTemplate = restTemplate;
        this.hubUrl = hubUrl;
    }

    /**
     * The sendFileToBank method takes in the bankId and the fileData.
     * The bankId represents the bank needs to be sent to. The Hub will identify the ID then parse the file to the correct bank.
     * fileData represents the file data to be sent.
     *
     * The function also calls the postForObject function to POST the request to the Hub.
     *
     * */
    public void sendFileToBank(String bankId, Bank.FileData fileData) {
        String url = hubUrl + "/hub/send-file/" + bankId;

        try {
            restTemplate.postForObject(url, fileData, ResponseEntity.class);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
    }
}