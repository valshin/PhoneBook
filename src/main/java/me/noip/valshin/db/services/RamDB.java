package me.noip.valshin.db.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.noip.valshin.db.Db;
import me.noip.valshin.db.entities.Note;

public class RamDB implements Db{
	private Map<String, Note> data = new HashMap<>();

	private String getKey(Note note){
		return note.getName()+note.getSecondName()+note.getLastName();
	}
	
	@Override
	public int addNote(Note note) {
		String key = getKey(note);
		if (data.containsKey(key)){
			return 1;
		}
		data.put(key, note);
		return 0;
	}

	@Override
	public int updateNote(Note note) {
		String key = getKey(note);
		if (data.containsKey(key)){
			data.put(key, note);
			return 0;
		}
		return 1;
	}

	@Override
	public int deleteNote(Note note) {
		String key = getKey(note);
		if (data.containsKey(key)){
			data.remove(key);
			return 0;
		}
		return 1;
	}
	
	@Override
	public List<Note> getData() {
		List<Note> copy = (List<Note>) data.values();
		return copy;
	}
	
	@Override
	public List<Note> getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Note> getByLastName(String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Note> getByPhone(String lastName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
