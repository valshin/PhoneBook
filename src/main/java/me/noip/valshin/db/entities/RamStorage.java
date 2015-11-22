package me.noip.valshin.db.entities;

import java.util.Map;

public class RamStorage {
	private Map<String, User> users;
	private Map<String, Note> notes;
	
	public Map<String, Note> getNotes() {
		return notes;
	}
	public void setNotes(Map<String, Note> notes) {
		this.notes = notes;
	}
	public Map<String, User> getUsers() {
		return users;
	}
	public void setUsers(Map<String, User> users) {
		this.users = users;
	}
}

