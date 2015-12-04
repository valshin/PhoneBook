package me.noip.valshin.db.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import me.noip.valshin.db.Db;
import me.noip.valshin.db.entities.Note;
import me.noip.valshin.db.entities.RamStorage;
import me.noip.valshin.db.entities.User;
import me.noip.valshin.exceptions.RamDbException;
import me.noip.valshin.security.ActiveUserAccessor;

public class RamDB implements Db {
	protected Logger logger = Logger.getLogger(RamDB.class.getName());
	protected RamStorage storage;
	protected Map<String, Note> notes;
	protected Map<String, User> users;
	
	@Autowired
    ActiveUserAccessor activeUserAccessor;

	public RamDB() {
		storage = new RamStorage();
		init();
	}

	protected void init() {
		users = storage.getUsers();
		notes = storage.getNotes();
	}

	public String getOwner() {
		User activeUser = activeUserAccessor.getActiveUser();
		String owner = activeUser.getLogin();
		logger.info("owner : " + owner);
		return owner;
	}

	private String getNoteKey(Note note) {
		return getOwner() + note.getName() + note.getSecondName() + note.getLastName();
	}

	@Override
	public void addNote(Note note) throws RamDbException {
		String key = getNoteKey(note);
		if (notes.containsKey(key)) {
			throw new RamDbException("Note with key '" + key + "' already exists");
		}
		note.setOwner(getOwner());
		notes.put(key, note);
	}

	@Override
	public void updateNote(Note note) throws RamDbException {
		String key = getNoteKey(note);
		if (!notes.containsKey(key)) {
			throw new RamDbException("No note with key '" + key + "'");
		}
		note.setOwner(getOwner());
		notes.put(key, note);
	}

	@Override
	public void saveNote(Note note) throws RamDbException {
		try {
			addNote(note);
		} catch (RamDbException addEx) {
			try {
				updateNote(note);
			} catch (RamDbException updEx) {
				throw new RamDbException(addEx.getMessage() + updEx.getMessage());
			}
		}
	}

	@Override
	public void deleteNote(Note note) throws RamDbException {
		String key = getNoteKey(note);
		if (!notes.containsKey(key)) {
			throw new RamDbException("No note with key '" + key + "'");
		}
		notes.remove(key);
	}

	@Override
	public List<Note> getNotesData() {
		List<Note> out = new ArrayList<>();
		for (Entry<String, Note> entry : notes.entrySet()) {
			Note note = entry.getValue();
			if (note.getOwner().equals(getOwner())) {
				out.add(note);
			}
		}
		return out;
	}

	@Override
	public List<Note> getByName(String name) {
		List<Note> out = new ArrayList<>();
		for (Entry<String, Note> entry : notes.entrySet()) {
			Note note = entry.getValue();
			if (note.getOwner().equals(getOwner()) && note.getName().equals(name)) {
				out.add(note);
			}
		}
		return out;
	}

	@Override
	public List<Note> getByLastName(String lastName) {
		List<Note> out = new ArrayList<>();
		for (Entry<String, Note> entry : notes.entrySet()) {
			Note note = entry.getValue();
			if (note.getOwner().equals(getOwner()) && note.getLastName().equals(lastName)) {
				out.add(note);
			}
		}
		return out;
	}

	@Override
	public List<Note> getByPhone(String phone) {
		List<Note> out = new ArrayList<>();
		for (Entry<String, Note> entry : notes.entrySet()) {
			Note note = entry.getValue();
			if (note.getOwner().equals(getOwner())
					&& (note.getPhone().equals(phone) || note.getHomePhone().equals(phone))) {
				out.add(note);
			}
		}
		return out;
	}

	private String getUserKey(User user) {
		return user.getLogin() + user.getPassword();
	}

	@Override
	public void addUser(User user) throws RamDbException {
		for (Entry<String, User> entry : users.entrySet()) {
			User u = entry.getValue();
			if (user.getLogin().equals(u.getLogin())) {
				throw new RamDbException("User with neame '" + user.getLogin() + "' already exist");
			}
		}
		users.put(getUserKey(user), user);
	}

	@Override
	public User getUser(String login, String password) {
		String key = login + password;
		return users.get(key);
	}
}
