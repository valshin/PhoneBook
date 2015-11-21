package me.noip.valshin.db.services;

import java.util.List;

import me.noip.valshin.db.Db;
import me.noip.valshin.db.entities.Note;
import me.noip.valshin.db.entities.User;

public class MySqlDB implements Db{

	@Override
	public int addNote(Note note) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateNote(Note note) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteNote(Note note) {
		// TODO Auto-generated method stub
		return 0;
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

	@Override
	public List<Note> getData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User getUser(String login, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}