package me.noip.valshin.db;

import java.util.Map;

import me.noip.valshin.db.entities.Note;
import me.noip.valshin.db.entities.User;

public interface Db{
	public String addNote(Note note);
	public void updateNote(Note note, String id);
	public void deleteNote(String id);
	public Map<String, Note> getByName(String name);
	public Map<String, Note> getByLastName(String lastName);
	public Map<String, Note> getByPhone(String phone);
	public Map<String, Note> getNotesData();
	
	public void addUser(User user);
	public User getUser(String login, String password);
}
