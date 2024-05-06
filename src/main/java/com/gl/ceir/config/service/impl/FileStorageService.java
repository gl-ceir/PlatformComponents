package com.gl.ceir.config.service.impl;

import com.gl.ceir.config.model.app.Notification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {
    @Value("${notificationattachment.filepath}")
    private String filepath;

//    @Autowired
//    private ApiFeignClient apiFeignClient;

    private static final Logger logger = LogManager.getLogger(FileStorageService.class);

    public String storeFile(Notification notification, MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        logger.info("File Name" + fileName);

        try {
            if (fileName.contains(".."))
                logger.error("Sorry! Filename contains invalid path sequence " + fileName);  // Check if the file's name contains invalid characters

            var fileStorageLocation = Paths.get(filepath + "/" + notification.getFeatureTxnId() + "/").toAbsolutePath().normalize();
            Files.createDirectories(fileStorageLocation);
            Path targetLocation = fileStorageLocation.resolve(fileName); // Copy file to the target location (Replacing existing file with the same name)
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileStorageLocation.toString();
        } catch (IOException ex) {
            logger.error("Could not store file " + fileName + ". Please try again!", ex);
            return null;
        }
    }

    /*  File Download */
    /*  */
    /*  */
    public Resource loadFileAsResource(String fileName) {

        var fileStorageLocation = Paths.get("/home/maverick/").toAbsolutePath().normalize();
        logger.info("File Storage Location " + fileStorageLocation);
        try {
            Files.createDirectories(fileStorageLocation);
        } catch (Exception ex) {
            logger.error("Could not create the directory where the uploaded files will be stored.", ex);
        }
        try {
            Path filePath = fileStorageLocation.resolve(fileName).normalize();
            logger.info("File filePath " + filePath);

            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                logger.error("File not found ie Resource Not exists " + fileName);
                return null;
            }
        } catch (MalformedURLException ex) {
            logger.error("File not found  " + fileName, ex);
            return null;
        }
    }


    /*  File Download */
    /*  */
    /*  */
    // Method to upload file using Feign Client
    public void uploadFile(String filePath) {
        try {
            File file = new File(filePath);
//            MultipartFile multipartFile = new MockMultipartFile("file", file.getName(),
//                    MediaType.MULTIPART_FORM_DATA_VALUE, FileUtils.readFileToByteArray(new File(filePath)));
//            String response = apiFeignClient.uploadMultipart(multipartFile, "sampleEmail", "subj", "message", "en", "txnID");
            //  logger.info("Response" + response);
        } catch (Exception e) {
            logger.error("Multipart Error" + e.getLocalizedMessage() + "::" + e + "::" + e.getCause().getCause().toString());
        }
        try {
            File file = new File(filePath);
            //      MultipartFile multipartFile = new MockMultipartFile("file", file.getName(),MediaType.MULTIPART_FORM_DATA_VALUE, FileUtils.readFileToByteArray(new File(filePath)));
            //  String response = apiFeignClient.uploadFile(file, "sampleEmail2", "sub2", "message2", "en", "txnID");
            //      logger.info("Response" + response);
        } catch (Exception e) {
            logger.error("File Error" + e.getLocalizedMessage() + "::" + e + "::" + e.getCause().getCause().toString());
        }
    }


    public void uploadRespFile(String filePath) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String apiUrl = "https://api.example.com/send-message";

            String languageCode = "km";
            HttpHeaders headers = new HttpHeaders();
            //  headers.setAcceptLanguage(); // Set the Khmer language code// Set the request headers
            headers.setContentType(MediaType.APPLICATION_JSON);
            // Create the message content in Khmer language
            String messageContent = "សួរស្ដី";
            // Create the request body
            String requestBody = "{\"message\": \"" + messageContent + "\"}";
            // Make the API call with the configured headers and message content
            ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, requestBody, String.class);

        } catch (Exception e) {
        }
    }

}
