package me.noip.valshin.db.entities;

public class User {
	private long id;
	private String login;
	private String password;
	private String fio;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String name) {
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
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public boolean isEqual(User user){
		return 
				getLogin().equals(user.getLogin()) && 
				getPassword().equals(user.getPassword()) &&
				getFio().equals(user.getFio());
	}
	
	public User clone(User user){
		User newUser = new User();
		newUser.setLogin(user.getLogin());
		newUser.setPassword(user.getPassword());
		newUser.setFio(user.getFio());
		return 	newUser;
	}
}

