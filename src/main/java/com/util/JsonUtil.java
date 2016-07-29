package com.util;

import java.util.List;
import java.util.Map;

import com.exception.BusinessException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	
    public static <T> String toJson(T model){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(model);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }
    
    public static <T> T toModel(String json, Class<T> classOfT){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return (T)objectMapper.readValue(json, classOfT);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        
    }
    
    @SuppressWarnings("unchecked")
    public static Map<String, Object> toMap(String json){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, Map.class);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        
    }
    
    public static <T> List<T> toList(String json, Class<T> classOfT){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JavaType javaType = objectMapper.getTypeFactory().constructParametrizedType(List.class,List.class, classOfT); 
            return objectMapper.readValue(json,javaType);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    
    public static void main(String[] arg){
    	
    }
}
