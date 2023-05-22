package com.microservice.bank;

import com.google.protobuf.ByteString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class BankService {

    private final HubServiceClient hubServiceClient;
    private final String fileStoragePath;

    public BankService(HubServiceClient hubServiceClient, @Value(value = "${bank.file.storage.path}") String fileStoragePath) {
        this.hubServiceClient = hubServiceClient;
        this.fileStoragePath = fileStoragePath;
    }

    public void sendFileToBank(String bankId, String fileName, byte[] fileContent) {
        // Create a FileData object with the file details
        Bank.FileData fileData = Bank.FileData.newBuilder()
                .setFileName(fileName)
                .setFileContent(ByteString.copyFrom(fileContent))
                .build();

        // Call the HubServiceClient to send the file to the specified bank
        hubServiceClient.sendFileToBank(bankId, fileData);
    }

    public void receiveFileFromHub(Bank.FileData fileData) {
        // Save the file to a specific directory or perform any required operations
        saveFile(fileData);
    }

    private void saveFile(Bank.FileData fileData) {
        // Save the file to the specified file storage path
        String fileName = fileData.getFileName();
        byte[] fileContent = fileData.getFileContent().toByteArray();

        // logic to save the file to the file storage path
        try {
            Path filePath = Paths.get(fileStoragePath, fileName);
            Files.write(filePath, fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
