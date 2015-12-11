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
@ContextConfiguration(classes={MySqlDBIntegrationTest.class, DataSourceConfig.class})
@Configuration
@TestPropertySource(
	properties = {
		"File_DB_Path=./src/test/resources/jsonrw.db", 
		"tablesQuery=./src/test/resources/testTablesQuery",
		"DB_Type=mysql",
		"DB_Host=localhost",
		"DB_Port=3306",
		"DB_Name=phonebook_by_valshin",
		"DB_User=root", 
		"DB_Password=root"
	}
)
public class MySqlDBIntegrationTest {
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public Config config(){
		return new Config();
	}
	
	@Autowired
	Config config;
	
	@Autowired
	DataSource dataSource;
	
	Connection connection = null;
    Statement statment = null;
    ResultSet resultSet = null;
    @Value("${tablesQuery}")
    String queryPath;
    String createTablesQuery;
	
	@Before
    public void initConnection() throws SQLException, IOException{
		if (createTablesQuery == null){
			createTablesQuery = "";
			DataInputStream is = new DataInputStream(new BufferedInputStream(new FileInputStream (new File(queryPath))));
	    	while (is.available() != 0) {
	    		createTablesQuery += is.readLine();
	    	}
	    	is.close();
		}
		System.out.println(createTablesQuery);
		connection = dataSource.getConnection();
        statment = connection.createStatement();
        statment.executeUpdate(createTablesQuery);
    }
	
	/*
	TODO: String s1 = "update emp set name='abc' where salary=984";
	String s2 = "insert into emp values ('Osama',1420)";  
	s.addBatch(s1);
	s.addBatch(s2);     
	s.executeBatch();
	*/
	
	@After
	public void closeConnection() throws SQLException{
		if(resultSet != null) resultSet.close();
        if(statment != null) {
        	statment.executeUpdate("DROP TABLE `testUsers`");
        	statment.executeUpdate("DROP TABLE `testNotes`");
        	statment.close();
        }
        if(connection != null) connection.close();
    }
	
	@Test
	public void testConnection() throws SQLException{
		resultSet = statment.executeQuery("SELECT 1");
        assertTrue(resultSet.next());
        assertEquals("1", resultSet.getString("1"));
	}
	
	@Test
	public void testRW() throws SQLException{
		String query = "INSERT INTO `users` SET `login` = 'testlogin', `pass` = 'testpass', `fio` = 'test fio'";
		int inserRowsCount = statment.executeUpdate(query);
		assertEquals(1, inserRowsCount);
		query = "SELECT * FROM `testUsers`";
		resultSet = statment.executeQuery(query);
		assertTrue(resultSet.next());
        assertEquals("0", resultSet.getString("id"));
        assertEquals("testlogin", resultSet.getString("login"));
        assertEquals("testpass", resultSet.getString("pass"));
        assertEquals("test fio", resultSet.getString("fio"));
	}
	
	
}
