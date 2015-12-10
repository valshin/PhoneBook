package me.noip.valshin.tools.io.services;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.AssertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import me.noip.valshin.config.Config;
import me.noip.valshin.db.entities.Note;
import me.noip.valshin.db.entities.MapStorage;
import me.noip.valshin.db.entities.User;
import me.noip.valshin.tools.generators.NotesMapGenerator;
import me.noip.valshin.tools.generators.UsersMapGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes={JsonRWIntegrationTest.class})
@Configuration
@TestPropertySource(
	properties = {
		"File_DB_Path=./src/test/resources/jsonrw.db", 
		"DB_Type=file",
		"DB_Host=localhost",
		"DB_Port=3306",
		"DB_Name=phonebook_by_valshin",
		"DB_User=root", 
		"DB_Password=nishlav2"
	}
)
public class JsonRWIntegrationTest {
	@Bean
	public JsonRW jsonRw(){
		return new JsonRW();	
	}
	
	@Bean
	public Config config(){
		return new Config();
	}
	
	@Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
       return new PropertySourcesPlaceholderConfigurer();
    }
	
	@Autowired 
	JsonRW jsonRw;
	
	@Autowired 
	Config config;
	
	@Test
	public void testPropertyLoading(){
		assertEquals("./src/test/resources/jsonrw.db", config.getFileDbPath());
	}
	
	@Test
	public void testRW(){
		MapStorage storageOut = new MapStorage();
		storageOut.setNotes(NotesMapGenerator.getRandomNotes(3, 10, 2, 10));
		storageOut.setUsers(UsersMapGenerator.getRandomUsers(3, 10));
		
		try {
			jsonRw.writeData(storageOut);
			MapStorage storageIn = jsonRw.readData();
			assertTrue(storageOut.isEqual(storageIn));
		} catch (IOException e) {
			throw new RuntimeException("Sumthng wrong!!!");
		}
	}
}
