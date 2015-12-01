package me.noip.valshin.db.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class RamStorage {
	private Map<String, User> users;
	private Map<String, Note> notes;
	
	public void init(){
		users = new HashMap<String, User>();
		notes = new HashMap<String, Note>();
	}
	
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
	
	public boolean isEqual(RamStorage storage) {
		for (Entry<String, User> entry : users.entrySet()){
			User u1 = entry.getValue();
			User u2 = storage.getUsers().get(entry.getKey());
		    if (!(u1.isEqual(u2))){
		    	return false;
		    }
		}
		for (Entry<String, Note> entry : notes.entrySet()){
			Note n1 = entry.getValue();
			Note n2 = storage.getNotes().get(entry.getKey());
		    if (!(n1.isEqual(n2))){
		    	return false;
		    }
		}
		return true;
	}
}

