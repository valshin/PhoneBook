package me.noip.valshin.db.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
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

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = { DataSourceIntegrationTest.class, DataSourceConfig.class, Config.class })
@Configuration
@TestPropertySource(properties = { "File_DB_Path=./src/test/resources/jsonrw.db",
		"tablesQuery=./src/test/resources/testTablesQuery", "DB_Type=mysql", "DB_Host=localhost", "DB_Port=3306",
		"DB_Name=phonebook_test", "DB_User=root", "DB_Password=root" })
public class DataSourceIntegrationTest {
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Autowired
	Config config;

	@Autowired
	DataSource dataSource;

	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	@Value("${tablesQuery}")
	String queryPath;
	String[] createTablesQuery;

	@SuppressWarnings("deprecation")
	@Before
	public void initConnection() throws SQLException, IOException {
		if (createTablesQuery == null) {
			String queryString = "";
			DataInputStream is = new DataInputStream(new BufferedInputStream(new FileInputStream(new File(queryPath))));
			while (is.available() != 0) {
				queryString += is.readLine();
			}
			is.close();
			createTablesQuery = queryString.split("divider");
		}
		connection = dataSource.getConnection();
		statement = connection.createStatement();
		for (String query : createTablesQuery) {
			statement.executeUpdate(query);
		}
	}

	@After
	public void closeConnection() throws SQLException {
		if (resultSet != null)
			resultSet.close();
		if (statement != null) {
			statement.executeUpdate("DROP TABLE `notes`");
			statement.executeUpdate("DROP TABLE `users`");
			statement.close();
		}
		if (connection != null)
			connection.close();
	}

	@Test
	public void testConnection() throws SQLException {
		resultSet = statement.executeQuery("SELECT 1");
		assertTrue(resultSet.next());
		assertEquals("1", resultSet.getString("1"));
	}

	@Test
	public void testRW() throws SQLException {
		String usersQuery = "INSERT INTO `users` SET `login`='логин1', `password`='testpass', `fio`='ФИО1'";
		int insertRowsCount = statement.executeUpdate(usersQuery);
		assertEquals(1, insertRowsCount);
		resultSet = statement.executeQuery("SELECT * FROM `users`");
		assertTrue(resultSet.next());
		assertEquals("1", resultSet.getString("id"));
		assertEquals("логин1", resultSet.getString("login"));
		assertEquals("testpass", resultSet.getString("password"));
		assertEquals("ФИО1", resultSet.getString("fio"));
		resultSet.close();

		String notesQuery = "INSERT INTO `notes` SET `owner`='1', `name`='имя1', `second_name`='отч1', `last_name`='фамилия1', `phone`='телефон1', `home_phone`='телефон2', `address`='адрес1', `email`='qwerty@asdf.zx'";
		insertRowsCount = statement.executeUpdate(notesQuery);
		assertEquals(1, insertRowsCount);
		resultSet = statement.executeQuery("SELECT * FROM `notes`");
		assertTrue(resultSet.next());
		assertEquals("1", resultSet.getString("id"));
		assertEquals("имя1", resultSet.getString("name"));
		assertEquals("отч1", resultSet.getString("second_name"));
		assertEquals("фамилия1", resultSet.getString("last_name"));
		assertEquals("телефон1", resultSet.getString("phone"));
		assertEquals("телефон2", resultSet.getString("home_phone"));
		assertEquals("адрес1", resultSet.getString("address"));
		assertEquals("qwerty@asdf.zx", resultSet.getString("email"));
		resultSet.close();
	}

	//TODO move to mysqldb test
//	public void testMultipleRW() throws SQLException {
//		User[] users = UserArrayGenerator.getRandomUsers(100, 200);
//		for (User user : users) {
//			Note[] notes = NoteArrayGenerator.getRandomNotes(100, 200);
//			this.writeUser(user);
//			for (Note note : notes) {
//				this.writeNote(note);
//			}
//		}
//
//	}
//
//	private void writeUser(User user) throws SQLException {
//		String query = String.format("INSERT INTO `testUsers` SET `login`='%s', `password`='%s', `fio`='%s'", 
//				user.getLogin(), 
//				user.getPassword(), 
//				user.getFio());
//		statement.executeUpdate(query);
//	}
//
//	private void writeNote(Note note) throws SQLException {
//		String query = "INSERT INTO `testNotes` SET ";
//		query += String.format("`name`='%s' ", note.getName());
//		query += String.format("`second_name`='%s' ", note.getSecondName());
//		query += String.format("`last_name`='%s' ", note.getLastName());
//		query += String.format("`phone`='%s' ", note.getPhone());
//		query += note.getHomePhone() != null ? String.format("`home_phone`='%s' ", note.getHomePhone()) : "";
//		query += note.getAddress() != null ? String.format("`address`='%s' ", note.getAddress()) : "";
//		query += note.getEmail() != null ? String.format("`email`='%s' ", note.getEmail()) : "";
//		statement.executeUpdate(query);
//	}
}
