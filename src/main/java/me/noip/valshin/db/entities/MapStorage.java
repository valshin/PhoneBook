package me.noip.valshin.db.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;

import me.noip.valshin.security.ActiveUserAccessor;

public class MapStorage {
	private Map<String, User> users;
	private Map<String, Map<String, Note>> notes;
	@Autowired
    ActiveUserAccessor activeUserAccessor;
	
	public void init(Map<String, User> usersData, Map<String, Map<String, Note>> notesData){
		this.users = usersData;
		this.notes = notesData;
	}
	
	public void init(){
		this.users = new HashMap<String, User>();
		this.notes = new HashMap<String, Map<String, Note>>();
	}
	
	public Map<String, Map<String, Note>> getNotes() {
		return notes;
	}
	
	public Map<String, Note> getUserNotes(String user) {
		Map<String, Note> userNotes = notes.get(user);
		if (userNotes == null) {
			userNotes = new HashMap<String, Note>();
			notes.put(user, userNotes);
		}
		return userNotes;
	}
	
	public Map<String, User> getUsers() {
		return users;
	}
	
	public void setUsers(Map<String, User> users) {
		this.users = users;
	}
	
	public void setNotes(Map<String, Map<String, Note>> notes) {
		this.notes = notes;
	}
	
	public boolean isEqual(MapStorage storage) {
		for (Entry<String, User> entry : users.entrySet()){
			User u1 = entry.getValue();
			User u2 = storage.getUsers().get(entry.getKey());
		    if (!(u1.isEqual(u2))){
		    	return false;
		    }
		}
		for (Entry<String, Map<String, Note>> userNotesEntry : notes.entrySet()){
			for (Entry<String, Note> noteEntry : userNotesEntry.getValue().entrySet()){
				Note n1 = noteEntry.getValue();
				Note n2 = storage.getNotes().get(userNotesEntry.getKey()).get(noteEntry.getKey());
			    if (!(n1.isEqual(n2))){
			    	return false;
			    }
			}
		}
		return true;
	}
}

