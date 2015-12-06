package me.noip.valshin.tools.generators;

import java.util.HashMap;
import java.util.Map;

import me.noip.valshin.db.entities.User;

public class UsersMapGenerator {
	public static Map<String, User> getRandomUsers(int min, int max){
		return getRandomUsers(RandomValuesGenerator.getRandomInt(min, max));
	}
	
	public static Map<String, User> getRandomUsers(int count){
		Map<String, User> users = new HashMap<String, User>();
		for (int i = 0; i < count; i++){
			User user = UserGenerator.getRandomUser();
			String key = user.getLogin() + user.getPassword();
			users.put(key, user);
		}
		return users;
	}
}
