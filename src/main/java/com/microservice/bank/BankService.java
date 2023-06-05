package com.microservice.bank;

import com.google.protobuf.ByteString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The BankService is another spring service component That takes in data passed by the BankController.
 * It is responsible for procesing files to be sent and received from and to other banks.
 * It is also responsible for saving the files.
 * */
@Service
public class BankService {

    private final HubServiceClient hubServiceClient;
    private final String fileStoragePath;

    /**
     * The constructor takes in The @HubServiceClient which is responsible for commuicating with the Hub.
     * and the filePath where the file will be stored once received.
     *
     * */
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

    /**
     * The receiveFileFromHub function is responsible for receiving the file from the Hub microservice
     * It takes a Bank.FileData object as a parameter then calls the saveFile method for further processing
     * */
    public void receiveFileFromHub(Bank.FileData fileData) {
        saveFile(fileData);
    }

    /**
     * The saveFile function is a helper method used to save the received file to a specific path.
     * It creates the path where the file will be saved and writes the file content to the specific path.
     * */
    private void saveFile(Bank.FileData fileData) {
        String fileName = fileData.getFileName();
        byte[] fileContent = fileData.getFileContent().toByteArray();

        try {
            Path filePath = Paths.get(fileStoragePath, fileName);
            Files.write(filePath, fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
