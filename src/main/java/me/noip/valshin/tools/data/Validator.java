package me.noip.valshin.tools.data;

public interface Validator {
//	Users
	public boolean checkLogin(String login);
	public boolean checkPassword(String password);
	public boolean checkFio(String fio);
//	Notes
	public boolean checkName(String name);
	public boolean checkSecondName(String secondName);
	public boolean checkLastName(String lastName);
	public boolean checkPhone(String phone);
	public boolean checkHomePhone(String homePhone);
	public boolean checkAdress(String adress);
	public boolean checkEmail(String email);
}
