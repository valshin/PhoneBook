package me.noip.valshin.tools.generators;

import me.noip.valshin.db.entities.User;

public class UserArrayGenerator {
	public static User[] getRandomUsers(int min, int max){
		return getRandomUsers(RandomValuesGenerator.getRandomInt(min, max));
	}
	
	public static User[] getRandomUsers(int count){
		User[] users = new User[count];
		for (int i = 0; i < count; i++){
			users[i] = UserGenerator.getRandomUser();
		}
		return users;
	}
}
