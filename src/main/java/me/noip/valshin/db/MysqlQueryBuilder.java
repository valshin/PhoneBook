package me.noip.valshin.db;

import java.util.Map;

import me.noip.valshin.db.entities.Note;
import me.noip.valshin.db.entities.User;

public interface MysqlQueryBuilder {
	public String notesRead(long owner);
	public String notesRead(long owner, Map<String, String> filters);
	public String notesRead(long owner, Map<String, String> filters, String compare, String connect);
	public String userRead(String login, String password);
	
	public String noteWrite(long owner, Note note);
	public String userWrite(User user);
	
	public String noteUpdate(long id, Note note);
	
	public String noteDelete(long id);
}
