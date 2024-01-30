package com.gl.ceir.config.controller;

import com.gl.ceir.config.service.impl.QRCodeGenerator;
import com.google.zxing.WriterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Map;

@Controller
public class QRCodeController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ApplicationContext applicationContext;

    @Value("${qrcode.filepath}")
    private String filepath;

    String QR_CODE_IMAGE_PATH = null;

    @PostConstruct
    public void init() {
        try {
            Resource resource = applicationContext.getResource("file:" + filepath + "/QRCode.png");
            QR_CODE_IMAGE_PATH = resource.getFile().getAbsolutePath();
            logger.info("File Path :{}", QR_CODE_IMAGE_PATH);
        } catch (IOException ioException) {
            logger.error("File Exception path:{} Error:{}", ioException.getMessage());
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/download/qrcode")
    @ResponseBody
    public ResponseEntity downloadQRCode(@RequestBody Map<String, String> data) {
        Path path = null;
        Resource resource = null;
        try {
            if (data.get("url") != null && data.size() == 1) // Generate and Save Qr Code Image in static/image folder
                path = QRCodeGenerator.generateQRCodeImage(data.get("url"), 250, 250, QR_CODE_IMAGE_PATH);
            else
                path = QRCodeGenerator.generateQRCodeImage(data, 250, 250, QR_CODE_IMAGE_PATH);
            resource = new UrlResource(path.toFile().toURI());
        } catch (Exception e) {
            logger.error("Resource not found", e.getMessage());
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    private String getQRCode(@RequestBody Map<String, String> data, Model model) {
        byte[] image = new byte[0];
        try {
            // Generate and Return Qr Code in Byte Array
            if (data.get("url") != null && data.size() == 1) {
                image = QRCodeGenerator.getQRCodeImage(data.get("url"), 250, 250);
            } else {
                image = QRCodeGenerator.getQRCodeImage(data, 250, 250);
            }
        } catch (WriterException | IOException e) {
            logger.error("Qr code generator", e.getMessage());
        }
        // Convert Byte Array into Base64 Encode String
        String qrcode = Base64.getEncoder().encodeToString(image);
        model.addAttribute("qrcode", qrcode);
        return "qrcode";
    }
}