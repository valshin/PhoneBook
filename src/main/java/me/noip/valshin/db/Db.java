package me.noip.valshin.db;

import java.util.List;

import me.noip.valshin.db.entities.Note;

public interface Db {
	public int addNote(Note note);
	public int updateNote(Note note);
	public int deleteNote(Note note);
	public List<Note> getByName(String name);
	public List<Note> getByLastName(String lastName);
	public List<Note> getByPhone(String lastName);
	public List<Note> getData();
}
