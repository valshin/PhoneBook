package me.noip.valshin.tools.io.requests;

import me.noip.valshin.db.entities.Note;

public class NotesRequestContext{
    private Note note;
    private String id;
	public Note getNote() {
		return note;
	}
	public String getId() {
		return id;
	}
}
