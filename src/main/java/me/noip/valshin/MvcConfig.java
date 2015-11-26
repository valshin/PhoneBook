package me.noip.valshin;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import me.noip.valshin.authentication.CustomAuthenticationProvider;
import me.noip.valshin.config.Config;
import me.noip.valshin.db.Db;
import me.noip.valshin.db.services.FileDB;
import me.noip.valshin.db.services.MySqlDB;
import me.noip.valshin.entities.constants.DbTypes;
import me.noip.valshin.exceptions.CoreException;
import me.noip.valshin.tools.data.Validator;
import me.noip.valshin.tools.data.services.DataValidator;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@PropertySource("file:${lardi.conf}")
public class MvcConfig {
	@Autowired 
	Config config;
	
	@Bean
	public Db db(){
		if (config.getDbType().equals(DbTypes.FILE)){
			try {
				return new FileDB();
			} catch (IOException e) {
				throw new CoreException("Can't read file at " + config.getFileDbPath());
			}
		}
		if (config.getDbType().equals(DbTypes.MYSQL)){
			return new MySqlDB();
		}
		throw new CoreException("unknown database type:" + config.getDbType());
	}
	
	@Bean
	public CustomAuthenticationProvider customAuthenticationProvider(){
		return new CustomAuthenticationProvider();
	}
	
	@Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
       return new PropertySourcesPlaceholderConfigurer();
    }
	
//	@Bean
//	public Config config(){
//		return new Config();
//	}
}
