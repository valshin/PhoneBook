package me.noip.valshin.controllers;

import me.noip.valshin.db.entities.Note;

public class RequestContext{
    private Note note;
    private String id;
	public Note getNote() {
		return note;
	}
	public String getId() {
		return id;
	}
}
