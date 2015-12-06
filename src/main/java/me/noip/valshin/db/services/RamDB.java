package me.noip.valshin.db.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import me.noip.valshin.db.Db;
import me.noip.valshin.db.entities.MapStorage;
import me.noip.valshin.db.entities.Note;
import me.noip.valshin.db.entities.User;
import me.noip.valshin.exceptions.RamDbException;
import me.noip.valshin.security.ActiveUserAccessor;

public class RamDB implements Db {
	protected Logger logger = Logger.getLogger(RamDB.class.getName());
	protected MapStorage storage;
	
	@Autowired
    ActiveUserAccessor activeUserAccessor;
	
	public void init(){
		storage = new MapStorage();
	}
	
	public void init(MapStorage storage){
		this.storage = storage;
	}

	public String getOwner() {
		return activeUserAccessor.getActiveUser().getLogin();
	}

	private String getNextNoteKey() {
		return Integer.toString(storage.getUserNotes(getOwner()).size());
	}

	@Override
	public String addNote(Note note) {
		String id = getNextNoteKey();
		storage.getUserNotes(getOwner()).put(id, note);
		return id;
	}

	@Override
	public void updateNote(Note note, String id) throws RamDbException {
		if (!storage.getUserNotes(getOwner()).containsKey(id)) {
			throw new RamDbException("No note with id '" + id + "'");
		}
		storage.getUserNotes(getOwner()).put(id, note);
	}

	@Override
	public void deleteNote(String id) throws RamDbException {
		if (!storage.getUserNotes(getOwner()).containsKey(id)) {
			throw new RamDbException("No note with id '" + id + "'");
		}
		storage.getUserNotes(getOwner()).remove(id);
	}

	@Override
	public Map<String, Note> getNotesData() {
		return new HashMap<String, Note>(storage.getUserNotes(getOwner()));
	}

	@Override
	public Map<String, Note> getByName(String name) {
		Map<String, Note> out = new HashMap<String, Note>();
		for (Entry<String, Note> entry : storage.getUserNotes(getOwner()).entrySet()) {
			Note note = entry.getValue();
			if (note.getName().equals(name)) {
				out.put(entry.getKey(), note.clone());
			}
		}
		return out;
	}

	@Override
	public Map<String, Note> getByLastName(String lastName) {
		Map<String, Note> out = new HashMap<String, Note>();
		for (Entry<String, Note> entry : storage.getUserNotes(getOwner()).entrySet()) {
			Note note = entry.getValue();
			if (note.getLastName().equals(lastName)) {
				out.put(entry.getKey(), note.clone());
			}
		}
		return out;
	}

	@Override
	public Map<String, Note> getByPhone(String phone) {
		Map<String, Note> out = new HashMap<String, Note>();
		for (Entry<String, Note> entry : storage.getUserNotes(getOwner()).entrySet()) {
			Note note = entry.getValue();
			if (note.getPhone().equals(phone) || note.getHomePhone().equals(phone)) {
				out.put(entry.getKey(), note.clone());
			}
		}
		return out;
	}

	private String getUserKey(User user) {
		return user.getLogin() + user.getPassword();
	}

	@Override
	public void addUser(User user) throws RamDbException {
		for (Entry<String, User> entry : storage.getUsers().entrySet()) {
			User u = entry.getValue();
			if (user.getLogin().equals(u.getLogin())) {
				throw new RamDbException("User with neame '" + user.getLogin() + "' already exist");
			}
		}
		storage.getUsers().put(getUserKey(user), user);
	}

	@Override
	public User getUser(String login, String password) {
		String key = login + password;
		return storage.getUsers().get(key);
	}
}
