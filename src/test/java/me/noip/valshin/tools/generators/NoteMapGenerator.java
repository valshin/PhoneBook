package me.noip.valshin.tools.generators;

import java.util.HashMap;
import java.util.Map;

import me.noip.valshin.db.entities.Note;

public class NoteMapGenerator {
	
	public static Map <String, Map<String, Note>> getRandomNotes(int usersMin, int usersMax, int notesMin, int notesMax){
		Map <String, Map<String, Note>> notes = new HashMap <String, Map<String, Note>>();
		int userCount = RandomValuesGenerator.getRandomInt(usersMin, usersMax);
		String[] users = getUsers(userCount);
		for (int i = 0; i < userCount; i++){
			int notesCount = RandomValuesGenerator.getRandomInt(notesMin, notesMax);
			notes.put(users[i], getUserNotes(notesCount));
		}
		return notes;
	}
	
	public static Map <String, Map<String, Note>> getRandomNotes(int userCount, int notesPerUserCount){
		return getRandomNotes(getUsers(userCount), notesPerUserCount);
	}
	
	public static Map <String, Map<String, Note>> getRandomNotes(String[] userCount, int notesPerUserCount){
		Map <String, Map<String, Note>> notes = new HashMap <String, Map<String, Note>>();
		for (int i = 0; i < userCount.length; i++){
			String user = userCount[i];
			notes.put(user, getUserNotes(notesPerUserCount));
		}
		return notes;
	}
	
	public static Map<String, Note> getUserNotes(int notesCount){
		Map<String, Note> userNotes = new HashMap<String, Note>(); 
		for (int i = 0; i < notesCount; i++){
			userNotes.put(Integer.toString(i), NoteGenerator.getRandomNote(false));
		}
		return userNotes;
	}
	
	public static String[] getUsers(int userCount){
		String[] users = new String[userCount];
		for (int i = 0; i < userCount; i++){
			users[i] = RandomValuesGenerator.getRandomString(5, 10);
		}
		return users;
	}
}
