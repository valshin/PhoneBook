package me.noip.valshin;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import me.noip.valshin.authentication.CustomAuthenticationProvider;
import me.noip.valshin.db.Db;
import me.noip.valshin.db.services.FileDB;
import me.noip.valshin.db.services.MySqlDB;
import me.noip.valshin.entities.constants.DbTypes;
import me.noip.valshin.exceptions.CoreException;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@PropertySource("file:${lardi.conf}")
public class MvcConfig {
	@Value("${dbType}")
	private String dbType;
	
	@Bean
	public Db db(){
		if (dbType.equals(DbTypes.FILE)){
			return new FileDB();
		}
		if (dbType.equals(DbTypes.MYSQL)){
			return new MySqlDB();
		}
		throw new CoreException("unknown database type:" + dbType);
	}
	
	@Bean
	public CustomAuthenticationProvider customAuthenticationProvider(){
		return new CustomAuthenticationProvider();
	}
	
	@Bean
	public ObjectMapper mapper(){
		return new ObjectMapper();
	}
	
//	public get
//	@bean
}
