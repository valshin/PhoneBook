package me.noip.valshin.db.services;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import me.noip.valshin.db.MysqlQueryBuilder;
import me.noip.valshin.db.entities.Note;
import me.noip.valshin.db.entities.User;

@Service
public class SimpleMysqlQueryBuilder implements MysqlQueryBuilder {
	private String userReadQuery = "SELECT `id`, `fio` from `users` WHERE `login` = '%s' AND `password` = '%s'";
	private String notesReadQuery = "SELECT `id`, `name`, `second_name`, `last_name`, `phone`, `home_phone`, `address`, `email`, `owner` from `notes`";
	private String userWriteQuery = "INSERT INTO `users` SET `login` = '%s', `password` = '%s', `fio` = '%s'";
	private String noteWriteQuery = "INSERT INTO `notes` SET `owner` = '%d', `name` = '%s', `second_name` = '%s', `last_name` = '%s', `phone` = '%s'";
	private String noteDeleteQuery = "DELETE FROM `notes` WHERE `id` = '%d'";
	private String noteUpdateQuery = "UPDATE `notes` SET `name` = '%s', `second_name` = '%s', `last_name` = '%s', `phone` = '%s'";

	@Override
	public String notesRead(long owner) {
		return notesReadQuery + " WHERE `owner` = '" + String.valueOf(owner) + "'";
	}
	
	@Override
	public String notesRead(long owner, Map<String, String> filters) {
		return notesRead(owner, filters, "LIKE", "OR");
	}

	@Override
	public String notesRead(long owner, Map<String, String> filters, String compare, String connect) {
		String[] queryArray = new String[filters.size()];
		int index = 0;
		for (Entry<String, String> entry: filters.entrySet()){
			queryArray[index] = "`" + entry.getKey() + "` " + compare + " '%" + entry.getValue() + "%'";
			index++;
		}
		return notesRead(owner) + " AND (" + String.join(" " + connect + " ", queryArray) + ")";
	}

	@Override
	public String userRead(String login, String password) {
		return String.format(userReadQuery, login, password);
	}

	@Override
	public String noteWrite(long owner, Note note) {
		String res = String.format(
				noteWriteQuery,
				owner,
				note.getName(), 
				note.getSecondName(), 
				note.getLastName(), 
				note.getPhone());
		
		if (note.getHomePhone() != null){
			res += ", `home_phone` = '" + note.getHomePhone() + "'";
		}
		if (note.getAddress() != null){
			res += ", `address` = '" + note.getAddress() + "'";
		}
		if (note.getEmail() != null){
			res += ", `email` = '" + note.getEmail() + "'";
		}
		return res;
	}

	@Override
	public String userWrite(User user) {
		return String.format(userWriteQuery, user.getLogin(), user.getPassword(), user.getFio());
	}

	@Override
	public String noteUpdate(long id, Note note) {
		String res = String.format(
				noteUpdateQuery,
				note.getName(), 
				note.getSecondName(), 
				note.getLastName(), 
				note.getPhone());
		
		if (note.getHomePhone() != null){
			res += ", `home_phone` = '" + note.getHomePhone() + "'";
		}
		if (note.getAddress() != null){
			res += ", `address` = '" + note.getAddress() + "'";
		}
		if (note.getEmail() != null){
			res += ", `email` = '" + note.getEmail() + "'";
		}
		res += " WHERE `id` = '" + Long.valueOf(id) + "'";
		return res;
	}

	@Override
	public String noteDelete(long id) {
		return String.format(noteDeleteQuery, id);
	}
}
