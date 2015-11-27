package me.noip.valshin.tools.io.services;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value="classpath:phonebook.properties")
public class JsonRWTestConfiguration {
	@Value("${File_DB_Path}")
	String path;
    
    @Bean
    public ObjectMapper mapper(){
    	return new ObjectMapper();
    }
    
    @Bean
    public JsonRW io(){
    	return new JsonRW();
    }
}
