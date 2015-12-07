package me.noip.valshin.tools.json;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import me.noip.valshin.exceptions.CoreException;

@Component
public class JsonHelper {  
	
    private static String OK_RESP = "OK"; 
    
    ObjectMapper mapper = new ObjectMapper();
    
    public String toJson(Object data){
    	try {
			return mapper.writeValueAsString(data);
		} catch (JsonProcessingException e) {
			throw new CoreException("Error serializing json", e);
		}
    }
    
	public <T> List<T> listFromJson(String jsonString, Class<T> type){
    	final CollectionType javaType = 
    		      mapper.getTypeFactory().constructCollectionType(List.class, type);
    	ObjectMapper mapper = new ObjectMapper();
		List<T> resultList;
		try {
			resultList = mapper.readValue(jsonString, javaType);
		} catch (IOException e) {
			throw new CoreException("Error deserializing json", e);
		}
		return resultList;
    }
    
    public String okAnswer() {
		return toJson(new JsonAnswer(OK_RESP, null));
	}
    public String jsonAnswer(Object obj) {
		return toJson(new JsonAnswer(obj, null));
	}
    
    public String errorAnswer(String error){
    	return toJson(new JsonAnswer(null, error));
    }
}