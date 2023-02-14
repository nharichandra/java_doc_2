package com.nisum.poc.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.poc.pojo.TaxationData;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Slf4j
public class StubUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String TAXATION_REQUEST = "json/request.json";
    private static final String TAXATION_RESPONSE = "json/response.json";

    public static String getTaxationDataJson(String type) {
        String jsonObject = null;
        try {
            TaxationData taxationData = type.equals("request") ? getTaxationDataRequest() : getTaxationDataResponse();
            jsonObject = objectMapper.writeValueAsString(taxationData);
        } catch (JsonProcessingException e) {
            log.error("Error occurred while Parsing taxationData.  Exception: {}", e.getMessage());
        }
        return jsonObject;
    }

    private static TaxationData getTaxationDataResponse() {
        return getTaxationData("response");
    }

    private static TaxationData getTaxationDataRequest() {
        return getTaxationData("request");
    }

    public static TaxationData getTaxationData(String type) {
        TaxationData taxationData = null;
        try {
            String fileName = type.equals("request") ? TAXATION_REQUEST : TAXATION_RESPONSE;
            taxationData = objectMapper.readValue(getFile(fileName), TaxationData.class);
        } catch (IOException e) {
            log.error("Error occurred while reading taxationResponse.json.  Exception: {}", e.getMessage());
        }
        return taxationData;
    }
    private static File getFile(String fileName) {
        ClassLoader classLoader = StubUtil.class.getClassLoader();
        return new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());
    }
}
