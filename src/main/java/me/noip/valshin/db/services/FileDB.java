package me.noip.valshin.db.services;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import me.noip.valshin.db.entities.Note;
import me.noip.valshin.db.entities.User;
import me.noip.valshin.exceptions.CoreException;
import me.noip.valshin.exceptions.RamDbException;
import me.noip.valshin.tools.io.services.JsonRW;
public class FileDB extends RamDB{
	protected Logger logger = Logger.getLogger(FileDB.class.getName());
	@Autowired
	private JsonRW io;
	@PostConstruct
	public void initBean() throws IOException{
		storage = io.readData();
		super.init();
	}
	
	private void write() throws IOException {
		io.writeData(storage);
	}
	
	@Override
	public void addNote(Note note) throws RamDbException{
		try {
			super.addNote(note);
			write();
		} catch (RamDbException e){
			throw e;
		} catch (IOException e){
			throw new CoreException("Read/Write Error");
		}
	}
	
	@Override
	public void updateNote(Note note) throws RamDbException {
		try {
			super.updateNote(note);
			write();
		} catch (RamDbException e){
			throw e;
		} catch (IOException e){
			throw new CoreException("Read/Write Error");
		}
	}
	
	@Override
	public void saveNote(Note note) throws RamDbException {
		try {
			super.saveNote(note);
			write();
		} catch (RamDbException e){
			throw e;
		} catch (IOException e){
			throw new CoreException("Read/Write Error");
		}
	}
	
	@Override
	public void deleteNote(Note note) throws RamDbException {
		try {
			super.deleteNote(note);
			write();
		} catch (RamDbException e){
			throw e;
		} catch (IOException e){
			throw new CoreException("Read/Write Error");
		}
	}
	
	@Override
	public void addUser(User user) throws RamDbException {
		try {
			super.addUser(user);
			write();
		} catch (RamDbException e){
			throw e;
		} catch (IOException e){
			throw new CoreException("Read/Write Error");
		}
	}
}
