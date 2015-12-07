package me.noip.valshin.tools.generators;

import me.noip.valshin.db.entities.User;

public class UserGenerator {
	public static User getRandomUser(){
		User user = new User();
		user.setLogin(RandomValuesGenerator.getRandomString(3, 10));
		user.setPassword(RandomValuesGenerator.getRandomString(5, 10));
		user.setFio(RandomValuesGenerator.getRandomString(5, 10));
		return user;
	}
}
