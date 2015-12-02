package me.noip.valshin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import me.noip.valshin.db.Db;
import me.noip.valshin.db.services.FileDB;
import me.noip.valshin.db.services.MySqlDB;
import me.noip.valshin.entities.constants.DbTypes;
import me.noip.valshin.exceptions.CoreException;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "me.noip.valshin")
@PropertySource("file:${lardi.conf}")
public class MvcConfig {
	@Autowired 
	Config config;
	
	@Bean
	public Db db(){
		if (config.getDbType().equals(DbTypes.FILE)){
			return new FileDB();
		}
		if (config.getDbType().equals(DbTypes.MYSQL)){
			return new MySqlDB();
		}
		throw new CoreException("unknown database type:" + config.getDbType());
	}
	
//	@Bean
//	public CustomAuthenticationProvider customAuthenticationProvider(){
//		return new CustomAuthenticationProvider();
//	}
//	
//	@Bean
//    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
//       return new PropertySourcesPlaceholderConfigurer();
//    }
}
