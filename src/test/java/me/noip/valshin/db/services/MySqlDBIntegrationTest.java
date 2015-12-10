package me.noip.valshin.db.services;

import javax.sql.DataSource;

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
import me.noip.valshin.config.DataSourceConfig;
import me.noip.valshin.tools.io.services.JsonRWIntegrationTest;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes={JsonRWIntegrationTest.class})
@Configuration
@TestPropertySource(
	properties = {
		"File_DB_Path=./src/test/resources/jsonrw.db", 
		"DB_Type=mysql",
		"DB_Host=localhost",
		"DB_Port=3306",
		"DB_Name=phonebook_by_valshin",
		"DB_User=root", 
		"DB_Password=nishlav2"
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
	
	@Bean(name = "dataSource")
	public DataSource dataSource() {
		return new DataSourceConfig().dataSource();
	}
	
	@Autowired
	DataSource dataSource;
	MySqlDB mySqlDb = new MySqlDB();
	
	@Test
	public void testConnectionParams(){
//		dataSource.getConnection().
	}
	
	
}
