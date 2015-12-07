package me.noip.valshin.db;

import java.util.Map;

import me.noip.valshin.db.entities.Note;
import me.noip.valshin.db.entities.User;
import me.noip.valshin.exceptions.RamDbException;

public interface Db{
	public String getOwner();
	public String addNote(Note note) throws RamDbException;
	public void updateNote(Note note, String id) throws RamDbException;
	public void deleteNote(String id) throws RamDbException;
	public Map<String, Note> getByName(String name);
	public Map<String, Note> getByLastName(String lastName);
	public Map<String, Note> getByPhone(String phone);
	public Map<String, Note> getNotesData();
	
	public void addUser(User user) throws RamDbException;
	public User getUser(String login, String password);
}
