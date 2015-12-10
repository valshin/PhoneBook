package me.noip.valshin.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceConfig {
	@Autowired
	Config config;
	@Bean(name = "dataSource")
	public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(config.getUrl());
        dataSource.setUsername(config.getMysqlDbUsername());
        dataSource.setPassword(config.getMysqlDbPassword());
        return dataSource;
    }
}
