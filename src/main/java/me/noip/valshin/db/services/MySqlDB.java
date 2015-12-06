package me.noip.valshin.db.services;

import java.util.Map;

import me.noip.valshin.db.Db;
import me.noip.valshin.db.entities.Note;
import me.noip.valshin.db.entities.User;
import me.noip.valshin.exceptions.RamDbException;
public class MySqlDB implements Db{

	@Override
	public String getOwner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addNote(Note note) throws RamDbException {
		// TODO Auto-generated method stub
		return null;
		
	}

	@Override
	public void updateNote(Note note, String id) throws RamDbException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteNote(String id) throws RamDbException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Note> getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Note> getByLastName(String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Note> getByPhone(String phone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Note> getNotesData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUser(User user) throws RamDbException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUser(String login, String password) {
		// TODO Auto-generated method stub
		return null;
	}
}