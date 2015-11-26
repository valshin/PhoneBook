package me.noip.valshin.db;

import java.util.List;

import me.noip.valshin.db.entities.Note;
import me.noip.valshin.db.entities.User;
import me.noip.valshin.exceptions.RamDbException;

public interface Db{
	public void addNote(Note note) throws RamDbException;
	public void updateNote(Note note) throws RamDbException;
	public void deleteNote(Note note) throws RamDbException;
	public List<Note> getByName(String name);
	public List<Note> getByLastName(String lastName);
	public List<Note> getByPhone(String phone);
	public List<Note> getNotesData();
	
	public void addUser(User user) throws RamDbException;
	public User getUser(String login, String password);
}
