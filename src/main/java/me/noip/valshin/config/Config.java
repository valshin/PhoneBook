package me.noip.valshin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Config {
	@Value("${File_DB_Path}")
	private String fileDbPath;
	@Value("${DB_Type}")
	private String dbType;
	@Value("${DB_Host}")
	private String dbHost;
	@Value("${DB_Port}")
	private String dbPort;
	@Value("${DB_Name}")
	private String mysqlDbName;
	@Value("${DB_User}")
	private String mysqlDbUser;
	@Value("${DB_Password}")
	private String mysqlDbPassword;
	
	public String getDbType() {
		return dbType;
	}
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}
	public String getFileDbPath() {
		return fileDbPath;
	}
	public void setFileDbPath(String fileDbPath) {
		this.fileDbPath = fileDbPath;
	}
	public String getDbHost() {
		return dbHost;
	}
	public void setDbHost(String dbHost) {
		this.dbHost = dbHost;
	}
	public String getDbPort() {
		return dbPort;
	}
	public void setDbPort(String dbPort) {
		this.dbPort = dbPort;
	}
	public String getMysqlDbName() {
		return mysqlDbName;
	}
	public void setMysqlDbName(String mysqlDbName) {
		this.mysqlDbName = mysqlDbName;
	}
	public String getMysqlDbUsername() {
		return mysqlDbUser;
	}
	public void setMysqlDbUser(String mysqlDbUser) {
		this.mysqlDbUser = mysqlDbUser;
	}
	public String getMysqlDbPassword() {
		return mysqlDbPassword;
	}
	public void setMysqlDbPassword(String mysqlDbPassword) {
		this.mysqlDbPassword = mysqlDbPassword;
	}
	
	public String getUrl(){
		return "jdbc:mysql://"+ dbHost + ":" + dbPort + "/" + mysqlDbName;
	}
}
