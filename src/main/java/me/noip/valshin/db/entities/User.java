package me.noip.valshin.db.entities;

public class User {
	private String login;
	private String password;
	private String fio;
	
	public String getLogin() {
		return login;
	}
	public void setName(String name) {
		this.login = name;
	}
	public String getFio() {
		return fio;
	}
	public void setFio(String fio) {
		this.fio = fio;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}

