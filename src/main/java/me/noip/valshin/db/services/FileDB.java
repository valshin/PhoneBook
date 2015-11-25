package me.noip.valshin.db.services;

import org.springframework.beans.factory.annotation.Autowired;

import me.noip.valshin.db.entities.Note;
import me.noip.valshin.db.entities.User;
import me.noip.valshin.tools.io.services.JsonRW;

public class FileDB extends RamDB{
	@Autowired
	private JsonRW io;
	
	public FileDB() {
		storage = io.readData();
		init();
	}
	
	private int write(){
		return io.writeData(storage);
	}
	
	@Override
	public int addNote(Note note) {
		return super.addNote(note) + write();
	}
	
	@Override
	public int updateNote(Note note) {
		return super.updateNote(note) + write();
	}
	
	@Override
	public int deleteNote(Note note) {
		return super.deleteNote(note) + write();
	}
	
	@Override
	public int addUser(User user) {
		return super.addUser(user) + write();
	}
}
