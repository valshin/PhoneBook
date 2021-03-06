package me.noip.valshin.config;

import static org.junit.Assert.assertEquals;

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

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes={ConfigTest.class, Config.class})
@Configuration
@TestPropertySource(
	properties = {
		"File_DB_Path=./src/test/resources/jsonrw.db", 
		"DB_Type=file",
		"DB_Host=localhost",
		"DB_Port=3306",
		"DB_Name=phonebook_by_valshin",
		"DB_User=root", 
		"DB_Password=root"
	}
)
public class ConfigTest {
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Autowired
	Config config;
	
	@Test
	public void testConfig(){
		assertEquals("./src/test/resources/jsonrw.db", config.getFileDbPath());
		assertEquals("file", config.getDbType());
		assertEquals("localhost", config.getDbHost());
		assertEquals("3306", config.getDbPort());
		assertEquals("phonebook_by_valshin", config.getMysqlDbName());
		assertEquals("jdbc:mysql://localhost:3306/phonebook_by_valshin?useUnicode=true&characterEncoding=UTF-8", config.getUrl());
		assertEquals("root", config.getMysqlDbUsername());
		assertEquals("root", config.getMysqlDbPassword());
	}
}
