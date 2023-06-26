package com.vendingmachine.api.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonUtil {
    
    private static ObjectMapper jacksonObjectMapper = new ObjectMapper();
    
    public static String convertToString(Object objectToBeWritten) {
        try {
            return jacksonObjectMapper.writeValueAsString(objectToBeWritten);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
