package me.noip.valshin.db.services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import me.noip.valshin.db.entities.Note;
import me.noip.valshin.db.entities.RamStorage;
import me.noip.valshin.db.entities.User;

public class FileDB extends RamDB{
	@Value("${File_DB_Path}")
	private String path;
	@Autowired
	private ObjectMapper mapper;
	
	public FileDB() {
		storage = readData();
		init();
	}
	
	private RamStorage readData(){
		try {
			return (RamStorage) mapper.readValue(new FileInputStream(path), RamStorage.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private int writeData(){
		try {
			mapper.writeValue(new FileOutputStream(path), storage);
			return 0;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	@Override
	public int addNote(Note note) {
		return super.addNote(note) + writeData();
	}
	
	@Override
	public int updateNote(Note note) {
		return super.updateNote(note) + writeData();
	}
	
	@Override
	public int deleteNote(Note note) {
		return super.deleteNote(note) + writeData();
	}
	
	@Override
	public int addUser(User user) {
		return super.addUser(user) + writeData();
	}
}
