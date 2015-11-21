package me.noip.valshin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import me.noip.valshin.db.Db;
import me.noip.valshin.db.services.RamDB;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@PropertySource("file:${lardi.conf}")
public class MvcConfig {
	@Value("${dbType}")
	private String dbType;
	
	@Bean
	public Db db(){
		return new RamDB();
//		if (dbType.equals(DbTypes.RAM)){
//		}
//		throw new CoreException("unknown database type:" + dbType);
	}
	
	@Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
    	DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/userbase");
		driverManagerDataSource.setUsername("root");
		driverManagerDataSource.setPassword("root");
		return driverManagerDataSource;
	}
     
}
