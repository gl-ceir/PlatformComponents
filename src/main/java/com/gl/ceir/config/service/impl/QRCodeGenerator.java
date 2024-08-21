package com.gl.ceir.config.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Map;


public class QRCodeGenerator {
    private static final Logger logger = LoggerFactory.getLogger(QRCodeGenerator.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static Path generateQRCodeImage(Map<String, String> data, int width, int height, String filePath) throws WriterException, IOException {
        return generateQRCodeImage(toTable(data), width, height, filePath);
    }

    public static Path generateQRCodeImage(String text, int width, int height, String filePath) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        Path path = FileSystems.getDefault().getPath(filePath);

        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        logger.info("QR Code Generated for text:{}", text);
        return path;
    }


    public static byte[] getQRCodeImage(Map<String, String> data, int width, int height) throws WriterException, IOException {
        return getQRCodeImage(toTable(data), width, height);
    }

    public static byte[] getQRCodeImage(String data, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height);
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageConfig con = new MatrixToImageConfig(0xFF000002, 0xFFFFC041);

        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream, con);
        byte[] pngData = pngOutputStream.toByteArray();
        logger.info("QR Code Image Generated for text:{}", data);
        return pngData;
    }

    private static String toJson(Map<String, String> data) {
        try {
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static String toTable(Map<String, String> data) {
        String tableData = "";
        for (String key : data.keySet()) {
            logger.info("File key " + key);
            tableData = tableData + key + ":" + data.get(key) + "\n";
            logger.info("TableData :" + tableData);
        }
        return tableData;
    }

}