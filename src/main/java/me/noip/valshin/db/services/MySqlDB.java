package me.noip.valshin.db.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import me.noip.valshin.db.Db;
import me.noip.valshin.db.entities.Note;
import me.noip.valshin.db.entities.User;
import me.noip.valshin.exceptions.RamDbException;
import me.noip.valshin.security.ActiveUserAccessor;
public class MySqlDB implements Db{
	@Autowired 
	DataSource dataSource;
	@Autowired
	ActiveUserAccessor activeUserAccessor;
	
	Connection connection;
	Statement statement;
	@PostConstruct
	public void initStorage() throws SQLException {
		connection = dataSource.getConnection();
		statement = connection.createStatement();
	}

	@Override
	public String getOwner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addNote(Note note) throws RamDbException {
		// TODO Auto-generated method stub
		return null;
		
	}

	@Override
	public void updateNote(Note note, String id) throws RamDbException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteNote(String id) throws RamDbException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Note> getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Note> getByLastName(String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Note> getByPhone(String phone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Note> getNotesData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUser(User user) throws RamDbException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUser(String login, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void writeUser(User user) throws SQLException {
		String query = String.format("INSERT INTO `testUsers` SET `login`='%s', `pass`='%s', `fio`='%s'", 
				user.getLogin(), 
				user.getPassword(), 
				user.getFio());
		statement.executeUpdate(query);
	}

	private void writeNote(Note note) throws SQLException {
		String query = "INSERT INTO `testNotes` SET ";
		query += String.format("`name`='%s' ", note.getName());
		query += String.format("`second_name`='%s' ", note.getSecondName());
		query += String.format("`last_name`='%s' ", note.getLastName());
		query += String.format("`phone`='%s' ", note.getPhone());
		query += note.getHomePhone() != null ? String.format("`home_phone`='%s' ", note.getHomePhone()) : "";
		query += note.getAddress() != null ? String.format("`address`='%s' ", note.getAddress()) : "";
		query += note.getEmail() != null ? String.format("`email`='%s' ", note.getEmail()) : "";
		statement.executeUpdate(query);
	}
	
	private void writeRelation(int userId, int noteId) throws SQLException {
		String query = String.format("INSERT INTO `testUsersNotes` SET `user`='%s', `note`='%s'", userId, noteId);
		statement.executeUpdate(query);
	}
}