package com.gl.ceir.config.service.impl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ChartService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public ChartService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public byte[] generateChartImage() throws IOException {
        String url = "https://export.highcharts.com/";

        Map<String, Object> chartOptions = new HashMap<>();
        chartOptions.put("type", "png");

        Map<String, Object> options = new HashMap<>();
        options.put("title", Map.of("text", "My Chart"));
        options.put("xAxis", Map.of("categories", new String[]{"Jan", "Feb", "Mar", "Apr", "May"}));
        options.put("yAxis", Map.of("title", Map.of("text", "Values")));
        options.put("series", new Object[]{
                Map.of("name", "Data", "data", new int[]{1, 3, 2, 4, 6})
        });

        chartOptions.put("options", options);

        String jsonString = objectMapper.writeValueAsString(chartOptions);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        IOUtils.write(jsonString, byteArrayOutputStream, "UTF-8");

        byte[] response = restTemplate.postForObject(url, byteArrayOutputStream.toByteArray(), byte[].class);

        return response;
    }
}