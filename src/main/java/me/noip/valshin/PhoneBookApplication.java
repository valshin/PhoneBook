package me.noip.valshin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import me.noip.valshin.db.Db;
import me.noip.valshin.db.services.RamDB;
import me.noip.valshin.entities.constants.DbTypes;
import me.noip.valshin.exceptions.CoreException;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
//@PropertySource("file:${lardi.conf}")
public class PhoneBookApplication {
//	@Value("${dbType}")
//	private String dbType;
	
	@Bean
	public Db db(){
		return new RamDB();
//		if (dbType.equals(DbTypes.RAM)){
//		}
//		throw new CoreException("unknown database type:" + dbType);
	}
	
    public static void main(String[] args) {
        SpringApplication.run(PhoneBookApplication.class, args);
    }
}
