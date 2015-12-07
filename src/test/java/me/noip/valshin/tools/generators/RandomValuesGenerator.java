package me.noip.valshin.tools.generators;

import java.util.Random;

public class RandomValuesGenerator {
	private static String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static String lower = "abcdefghijklmnopqrstuvwxyz";
	private static String digits = "0123456789";
	public static Random random = new Random();
	
	public static boolean getRandomBoolean(){
		return random.nextBoolean();
	}
	
	public static int getRandomInt(){
		return random.nextInt();
	}
	
	public static int getRandomInt(int min, int max){
		return min + random.nextInt(max - min);
	}
	
	public static String getRandomString(int rangeFrom, int rangeTo){
		int length = getRandomInt(rangeFrom, rangeTo);
		return getRandomString(length);
	}
	
	public static String getRandomString(int rangeFrom, int rangeTo, String characters){
		int length = getRandomInt(rangeFrom, rangeTo);
		return getRandomString(length, characters);
	}
	
	public static String getRandomString(int length){
		String characters = upper + lower + digits;
	    return getRandomString(length, characters);
	}
	
	public static String getRandomString(int length, String characters){
		char[] text = new char[length];
	    for (int i = 0; i < length; i++){
	        text[i] = characters.charAt(random.nextInt(characters.length()));
	    }
	    return new String(text);
	}
	
	public static String getRandomEmail(){
		return getRandomString(3, 8, lower+upper) + 
				"@" + 
				getRandomString(3, 8, lower+upper) + 
				"." + 
				getRandomString(2, 3, lower+upper);
	}
	
	public static String getRandomPhone(){
		return "+380(" +
				getRandomString(2, digits) + 
				")" + 
				getRandomString(7, digits);
	}
}
