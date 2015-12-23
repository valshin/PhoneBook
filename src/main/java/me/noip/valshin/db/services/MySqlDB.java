package me.noip.valshin.db.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import me.noip.valshin.db.Db;
import me.noip.valshin.db.MysqlQueryBuilder;
import me.noip.valshin.db.entities.Note;
import me.noip.valshin.db.entities.User;
import me.noip.valshin.exceptions.CoreException;
import me.noip.valshin.security.ActiveUserAccessor;
public class MySqlDB implements Db{
	@Autowired 
	DataSource dataSource;
	@Autowired
	ActiveUserAccessor activeUserAccessor;
	@Autowired
	MysqlQueryBuilder queryBuilder;
	
	Connection connection;
	Statement statement;
	@PostConstruct
	private void initConnection() throws SQLException {
		connection = dataSource.getConnection();
		statement = connection.createStatement();
	}
	
	private void closeConnection() throws SQLException {
		if (connection != null) connection.close();
		if (statement != null) statement.close();
	}
	
	@Override
	public String addNote(Note note) {
		note.setOwner(getOwner());
		try {
			return writeNote(note);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CoreException(e);
		}
	}

	@Override
	public void updateNote(Note note, String id) {
		try {
			System.out.println(queryBuilder.noteUpdate(Long.valueOf(id), note));
			statement.executeUpdate(queryBuilder.noteUpdate(Long.valueOf(id), note));
		} catch (NumberFormatException | SQLException e) {
			System.out.println(e.getMessage());
			throw new CoreException("Could not update note", e);
		}
		
	}

	@Override
	public void deleteNote(String id) {
		try {
			statement.executeUpdate(queryBuilder.noteDelete(Long.valueOf(id)));
		} catch (NumberFormatException | SQLException e) {
			throw new CoreException("Could not delete note", e);
		}
	}

	@Override
	public Map<String, Note> getByName(String name) {
		try {
			ResultSet resultSet = statement.executeQuery(queryBuilder.notesRead(getOwner(), new HashMap<String, String>(){{
				put("name", name);
			}}));
			Map<String, Note> out = makeNotes(resultSet);
			resultSet.close();
			return out;
		} catch (SQLException e) {
			throw new CoreException("SQL error", e);
		}
	}

	@Override
	public Map<String, Note> getByLastName(String lastName) {
		try {
			ResultSet resultSet = statement.executeQuery(queryBuilder.notesRead(getOwner(), new HashMap<String, String>(){{
				put("last_name", lastName);
			}}));
			Map<String, Note> out = makeNotes(resultSet);
			resultSet.close();
			return out;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CoreException("SQL error", e);
		}
	}

	@Override
	public Map<String, Note> getByPhone(String phone) {
		try {
			ResultSet resultSet = statement.executeQuery(queryBuilder.notesRead(getOwner(), new HashMap<String, String>(){{
				put("phone", phone);
				put("home_phone", phone);
			}}));
			Map<String, Note> out = makeNotes(resultSet);
			resultSet.close();
			return out;
		} catch (SQLException e) {
			throw new CoreException("SQL error", e);
		}
	}

	@Override
	public Map<String, Note> getNotesData() {
		try {
			ResultSet resultSet = statement.executeQuery(queryBuilder.notesRead(getOwner()));
			Map<String, Note> out = makeNotes(resultSet);
			resultSet.close();
			return out;
		} catch (SQLException e) {
			throw new CoreException("SQL error", e);
		}
	}
	
	public long getOwner() {
		return activeUserAccessor.getActiveUserName().getId();
	}
	
	private String writeNote(Note note) throws SQLException {
		int affectedRows = statement.executeUpdate(queryBuilder.noteWrite(getOwner(), note));
		
		if (affectedRows == 0) {
			throw new SQLException("Creating note failed, no rows affected.");
		}
		
		ResultSet resultSet = statement.getGeneratedKeys();
		if (resultSet.next()) {
			String resId = String.valueOf(resultSet.getLong(1));
			resultSet.close();
			return resId;
		} else {
			throw new SQLException("Creating note failed, no ID obtained.");
		}
	}
	
	private Note makeNote(ResultSet resultSet) throws SQLException{
		Note note = new Note();
		note.setOwner(resultSet.getLong("owner"));
		note.setName(resultSet.getString("name"));
		note.setSecondName(resultSet.getString("second_name"));
		note.setLastName(resultSet.getString("last_name"));
		note.setPhone(resultSet.getString("phone"));
		if (resultSet.getString("home_phone") != null){
			note.setHomePhone(resultSet.getString("home_phone"));
		}
		if (resultSet.getString("address") != null){
			note.setAddress(resultSet.getString("address"));
		}
		if (resultSet.getString("email") != null){
			note.setEmail(resultSet.getString("email"));
		}
		return note;
	}
	
	private Map<String, Note> makeNotes(ResultSet resultSet) throws SQLException{
		Map<String, Note> out = new HashMap<>();
		while (resultSet.next()){
			String key = String.format("%d", resultSet.getLong("id"));
			out.put(key, makeNote(resultSet));
		}
		return out;
	}

	@Override
	public void addUser(User user) {
		try {
			writeUser(user);
		} catch (SQLException e) {
			throw new CoreException("Could not add user", e);
		}
		
	}

	@Override
	public User getUser(String login, String password) {
		try {
			ResultSet resultSet = statement.executeQuery(queryBuilder.userRead(login, password));
			if (resultSet.next()){
				User user = makeUser(resultSet, login, password);
				resultSet.close();
				return user;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new CoreException("SQL error", e);
		}
		return null;
	}
	
	private void writeUser(User user) throws SQLException {
		statement.executeUpdate(queryBuilder.userWrite(user));
	}

	
	private User makeUser(ResultSet resultSet, String login, String password) throws SQLException{
		User user = new User();
		user.setLogin(login);
		user.setPassword(password);
		user.setFio(resultSet.getString("fio"));
		user.setId(resultSet.getLong("id"));
		return user;
	}
}