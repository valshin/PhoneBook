package me.noip.valshin.db.services;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import me.noip.valshin.config.Config;
import me.noip.valshin.config.DataSourceConfig;
import me.noip.valshin.db.Db;
import me.noip.valshin.db.entities.Note;
import me.noip.valshin.db.entities.User;
import me.noip.valshin.entities.constants.DbTypes;
import me.noip.valshin.security.TestActiveUserAccessor;
import me.noip.valshin.tools.generators.NoteArrayGenerator;
import me.noip.valshin.tools.generators.NoteGenerator;
import me.noip.valshin.tools.generators.UserGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = { 
		MySqlDBIntegrationTest.class, 
		DataSourceConfig.class, 
		Config.class, 
		TestActiveUserAccessor.class, 
		SimpleMysqlQueryBuilder.class})
@Configuration
@TestPropertySource(properties = { "File_DB_Path=./src/test/resources/jsonrw.db",
		"tablesQuery=./src/test/resources/testTablesQuery", "DB_Type=mysql", "DB_Host=localhost", "DB_Port=3306",
		"DB_Name=phonebook_by_valshin", "DB_User=root", "DB_Password=root" })
public class MySqlDBIntegrationTest {
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	@Value("${tablesQuery}")
	String queryPath;
	@Autowired
	Config config;
	@Autowired
	TestActiveUserAccessor activeUserAccessor;
	@Bean
	public Db db(){
		if (config.getDbType().equals(DbTypes.MYSQL)){
			return new MySqlDB();
		}
		return null;
	}
	
	@Autowired
	DataSource dataSource;
	
	@After
	public void closeConnection() throws SQLException {
		Connection connection = dataSource.getConnection();
		Statement statement = connection.createStatement();
		statement.executeUpdate("DELETE FROM `notes`");
		statement.executeUpdate("DELETE FROM `users`");
		if (statement != null) {
			statement.close();
		}
		if (connection != null){
			connection.close();
		}
	}
	
	@Autowired
	Db db;
	
	@Test
	public void testUserRW(){
		User user = UserGenerator.getRandomUser();
		db.addUser(user);
		User inUser = db.getUser(user.getLogin(), user.getPassword());
		assertTrue(user.isEqual(inUser));
		assertNull(db.getUser("login", "pasword"));
		assertNull(db.getUser("logn", "password"));
		assertNull(db.getUser("", ""));
	}
	
	@Test
	public void testNoteRW(){
		User user = UserGenerator.getRandomUser();
		user.setId(33L);
		activeUserAccessor.setActiveUser(user);
		Note note = NoteGenerator.getRandomNote(true);
		db.addNote(note);
		Map <String, Note> notes = db.getByLastName(note.getLastName());
		assertTrue(notes.size() == 1);
		String noteId = (String) notes.keySet().toArray()[0];
		assertTrue(notes.get(noteId).equals(note));
		notes = db.getByName(note.getName());
		assertTrue(notes.get(noteId).equals(note));
		notes = db.getByPhone(note.getPhone());
		assertTrue(notes.get(noteId).equals(note));
		notes = db.getByPhone(note.getHomePhone());
		assertTrue(notes.get(noteId).equals(note));
		
		assertTrue(db.getByName("123").size() == 0);
		assertTrue(db.getByLastName("123").size() == 0);
		assertTrue(db.getByPhone("123").size() == 0);
	}
	
	@Test
	public void testMultipleNotesRW(){
		Map <User, Note[]> map = new HashMap<>();
		for (int i = 1; i < 10; i++){
			User user = UserGenerator.getRandomUser();
			db.addUser(user);
			user = db.getUser(user.getLogin(), user.getPassword());
			activeUserAccessor.setActiveUser(user);
			Note[] notesArray = NoteArrayGenerator.getRandomNotes(2, 50);
			for (Note note : notesArray){
				db.addNote(note);
			}
			map.put(user, notesArray);
		}
		
		for (Entry <User, Note[]> entry : map.entrySet()){
			User user = entry.getKey();
			activeUserAccessor.setActiveUser(user);
			Map <String, Note> notes = db.getNotesData();
			assertTrue(notes.size() == entry.getValue().length);
		}
	}
	
	@Test
	public void testMultipleNotesRW2(){
		Map <User, Note[]> map = new HashMap<>();
		User[] users = new User[3];
		User user = UserGenerator.getRandomUser();
		db.addUser(user);
		user = db.getUser(user.getLogin(), user.getPassword());
		activeUserAccessor.setActiveUser(user);
		users[0] = user;
		Note[] notesArray = NoteArrayGenerator.getRandomNotes(3);
		for (Note note: notesArray){
			note.setHomePhone("123456789011");
			db.addNote(note);
		}
		notesArray = NoteArrayGenerator.getRandomNotes(5);
		for (Note note: notesArray){
			note.setPhone("123456789011");
			db.addNote(note);
		}
		
		notesArray = NoteArrayGenerator.getRandomNotes(4);
		for (Note note: notesArray){
			note.setLastName("lastname");
			db.addNote(note);
		}
		notesArray = NoteArrayGenerator.getRandomNotes(6);
		for (Note note: notesArray){
			note.setName("name");
			db.addNote(note);
		}
		
		user = UserGenerator.getRandomUser();
		db.addUser(user);
		user = db.getUser(user.getLogin(), user.getPassword());
		activeUserAccessor.setActiveUser(user);
		users[1] = user;
		notesArray = NoteArrayGenerator.getRandomNotes(1);
		for (Note note: notesArray){
			note.setHomePhone("098765432109");
			db.addNote(note);
		}
		notesArray = NoteArrayGenerator.getRandomNotes(7);
		for (Note note: notesArray){
			note.setPhone("000000000001");
			db.addNote(note);
		}
		
		notesArray = NoteArrayGenerator.getRandomNotes(13);
		for (Note note: notesArray){
			note.setLastName("lastname2");
			db.addNote(note);
		}
		notesArray = NoteArrayGenerator.getRandomNotes(12);
		for (Note note: notesArray){
			note.setName("name2");
			db.addNote(note);
		}
		
		user = UserGenerator.getRandomUser();
		db.addUser(user);
		user = db.getUser(user.getLogin(), user.getPassword());
		activeUserAccessor.setActiveUser(user);
		users[2] = user;
		notesArray = NoteArrayGenerator.getRandomNotes(2);
		for (Note note: notesArray){
			note.setHomePhone("1111111111");
			db.addNote(note);
		}
		notesArray = NoteArrayGenerator.getRandomNotes(8);
		for (Note note: notesArray){
			note.setPhone("2222222222");
			db.addNote(note);
		}
		
		notesArray = NoteArrayGenerator.getRandomNotes(9);
		for (Note note: notesArray){
			note.setLastName("lastname3");
			db.addNote(note);
		}
		notesArray = NoteArrayGenerator.getRandomNotes(11);
		for (Note note: notesArray){
			note.setName("name3");
			db.addNote(note);
		}
		
		activeUserAccessor.setActiveUser(users[0]);
		Map <String, Note> notes = db.getByLastName("lastn");
		assertTrue(notes.size() == 4);
		notes = db.getByLastName("qwerty");
		assertTrue(notes.size() == 0);
		notes = db.getByName("name");
		assertTrue(notes.size() == 6);
		notes = db.getByName("qwerty");
		assertTrue(notes.size() == 0);
		notes = db.getByPhone("1234567");
		assertTrue(notes.size() == 8);
		Entry <String, Note> noteEntry = notes.entrySet().iterator().next();
		Note note = noteEntry.getValue();
		notes = db.getByPhone("000000");
		assertTrue(notes.size() == 0);
		note.setName("name");
		db.updateNote(note, noteEntry.getKey());
		notes = db.getByName("name");
		assertTrue(notes.size() == 7);
		
		activeUserAccessor.setActiveUser(users[1]);
		notes = db.getByLastName("me2");
		assertTrue(notes.size() == 13);
		notes = db.getByLastName("qwerty");
		assertTrue(notes.size() == 0);
		notes = db.getByName("me2");
		assertTrue(notes.size() == 12);
		notes = db.getByName("qwerty");
		assertTrue(notes.size() == 0);
		notes = db.getByPhone("32109");
		assertTrue(notes.size() == 1);
		notes = db.getByPhone("000000");
		assertTrue(notes.size() == 7);
		noteEntry = notes.entrySet().iterator().next();
		note = noteEntry.getValue();
		db.deleteNote(noteEntry.getKey());
		notes = db.getByPhone("000000");
		assertTrue(notes.size() == 6);
		
		activeUserAccessor.setActiveUser(users[2]);
		notes = db.getByLastName("lastname3");
		assertTrue(notes.size() == 9);
		notes = db.getByLastName("qwerty");
		assertTrue(notes.size() == 0);
		notes = db.getByName("name3");
		assertTrue(notes.size() == 11);
		notes = db.getByName("qwerty");
		assertTrue(notes.size() == 0);
		notes = db.getByPhone("1111");
		assertTrue(notes.size() == 2);
		notes = db.getByPhone("2222");
		assertTrue(notes.size() == 8);
	}
}
