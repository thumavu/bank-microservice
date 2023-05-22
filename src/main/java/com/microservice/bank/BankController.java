package com.microservice.bank;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * The BankController is responsible for handling HTTP requests
 * and defining the endpoints for the Banks microservice
 * */
@RestController
@RequestMapping("/bank")
public class BankController {

    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/receive-file")
    public ResponseEntity<String> receiveFile(@RequestBody Bank.FileData fileData) {
        bankService.receiveFileFromHub(fileData);
        return ResponseEntity.ok("File received successfully");
    }

    @PostMapping("/send-file/{bankId}")
    public ResponseEntity<String> sendFileToBank(@PathVariable String bankId, @RequestParam("fileName") String fileName, @RequestParam("fileContent") MultipartFile fileContent) {
        try {
            byte[] fileBytes = fileContent.getBytes();
            bankService.sendFileToBank(bankId, fileName, fileBytes);
            return ResponseEntity.ok("File sent to Bank " + bankId + " successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send file to Bank " + bankId);
        }
    }
}
