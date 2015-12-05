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

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes={JsonRWIntegrationTest.class})
@Configuration
@TestPropertySource(
	properties = {
		"File_DB_Path=./src/test/resources/jsonrw.db", 
		"DB_Type=file", "DB_Host=localhost", 
		"DB_Name=phonebook_by_valshin", 
		"DB_Login=root", 
		"DB_Password=root"
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
		Map<String, Note> notes = new HashMap<>();
		Map<String, User> users = new HashMap<>();
		for (int i = 0; i < 10; i++){
			addNote(notes, i);
		}
		for (int i = 0; i < 10; i++){
			addUser(users, i);
		}
		storageOut.setNotes(notes);
		storageOut.setUsers(users); 
		
		try {
			jsonRw.writeData(storageOut);
			MapStorage storageIn = jsonRw.readData();
			assertTrue(storageOut.isEqual(storageIn));
		} catch (IOException e) {
			throw new RuntimeException("Sumthng wrong!!!");
		}
	}

	private Note getNote(int num){
		Note note = new Note();
		note.setName("N" + num);
		note.setSecondName("Sn" + num);
		note.setLastName("Ln" + num);
		note.setPhone("050000000" + num);
		note.setOwner(Integer.toString(num % 3));
		return note;
	}
	
	private User getUser(int num){
		User user = new User();
		user.setFio("fio" + num);
		user.setLogin("login" + num);
		user.setPassword("password" + num);
		return user;
	}
	
	private String getNoteKey(int num){
		return "N" + num + "Sn" + num + "Ln" + num;
	}
	
	private String getUserKey(int num){
		return "login" + num + "password" + num;
	}
	
	private void addNote(Map<String, Note> map, int num){
		map.put(getNoteKey(num), getNote(num));
	}
	private void addUser(Map<String, User> map, int num){
		map.put(getUserKey(num), getUser(num));
	}
}
