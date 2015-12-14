package me.noip.valshin.db.services;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
import me.noip.valshin.exceptions.RamDbException;
import me.noip.valshin.security.TestActiveUserAccessor;
import me.noip.valshin.tools.generators.NoteArrayGenerator;
import me.noip.valshin.tools.generators.RandomValuesGenerator;
import me.noip.valshin.tools.generators.UserArrayGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = { MySqlDBIntegrationTest.class, DataSourceConfig.class, Config.class, TestActiveUserAccessor.class })
@Configuration
@TestPropertySource(properties = { "File_DB_Path=./src/test/resources/jsonrw.db",
		"tablesQuery=./src/test/resources/testTablesQuery", "DB_Type=mysql", "DB_Host=localhost", "DB_Port=3306",
		"DB_Name=phonebook_by_valshin", "DB_User=root", "DB_Password=root" })
public class MySqlDBIntegrationTest {
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
	Db db;
	public void testMultipleRW() throws SQLException, RamDbException {
		
		User[] users = UserArrayGenerator.getRandomUsers(100, 200);
		Note[][] notes = new Note[users.length][RandomValuesGenerator.getRandomInt(100, 200)];
		int userId = 0;
		for (User user : users) {
			activeUserAccessor.setActiveUser(user);
			notes[userId] = NoteArrayGenerator.getRandomNotes(notes[userId].length);
			db.addUser(user);
			for (Note note : notes[userId]) {
				db.addNote(note);
			}
			userId++;
		}
		userId = 0;
		for (User user : users) {
			activeUserAccessor.setActiveUser(user);
			assertTrue(user.equals(db.getUser(user.getLogin(), user.getPassword())));
			Map<String, Note> dbNotes = db.getNotesData();
			assertTrue(dbNotes.size() == notes[userId].length);
			int noteId = 0;
			for (Entry <String, Note> noteEntry : dbNotes.entrySet()){
				assertTrue(noteEntry.getValue().equals(notes[userId][noteId]));
				noteId++;
			}
			userId++;
		}

	}
}
