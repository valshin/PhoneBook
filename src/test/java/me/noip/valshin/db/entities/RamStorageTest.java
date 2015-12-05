package me.noip.valshin.db.entities;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.mockito.Mockito;

public class RamStorageTest {
	
	@Test
	public void testGettersAndSetters(){
		MapStorage storage = new MapStorage();
		Map<String, Note> notes = new HashMap<>();
		Map<String, User> users = new HashMap<>();
		for (int i = 0; i < 9; i++){
			addNote(notes, i);
		}
		for (int i = 0; i < 3; i++){
			addUser(users, i);
		}
		storage.setNotes(notes);
		storage.setUsers(users);
		assertEquals(notes, storage.getNotes());
		assertEquals(users, storage.getUsers());
		
		assertEquals("0", notes.get("N0Sn0Ln0").getOwner());
		assertEquals("N0", notes.get("N0Sn0Ln0").getName());
		assertEquals("Sn0", notes.get("N0Sn0Ln0").getSecondName());
		assertEquals("Ln0", notes.get("N0Sn0Ln0").getLastName());
		
		assertEquals("1", notes.get("N4Sn4Ln4").getOwner());
		assertEquals("N4", notes.get("N4Sn4Ln4").getName());
		assertEquals("Sn4", notes.get("N4Sn4Ln4").getSecondName());
		assertEquals("Ln4", notes.get("N4Sn4Ln4").getLastName());

		assertEquals("2", notes.get("N8Sn8Ln8").getOwner());
		assertEquals("N8", notes.get("N8Sn8Ln8").getName());
		assertEquals("Sn8", notes.get("N8Sn8Ln8").getSecondName());
		assertEquals("Ln8", notes.get("N8Sn8Ln8").getLastName());
		
	}
	
	private Note getNote(int num){
		Note note = Mockito.mock(Note.class);
		Mockito.when(note.getName()).thenReturn("N" + num);
		Mockito.when(note.getSecondName()).thenReturn("Sn" + num);
		Mockito.when(note.getLastName()).thenReturn("Ln" + num);
		Mockito.when(note.getOwner()).thenReturn(Integer.toString(num % 3));
		return note;
	}
	
	private User getUser(int num){
		User user = Mockito.mock(User.class);
		Mockito.when(user.getFio()).thenReturn("fio" + num);
		Mockito.when(user.getLogin()).thenReturn("login" + num);
		Mockito.when(user.getPassword()).thenReturn("password" + num);
		return user;
	}
	
	private String getNoteKey(int num){
		return "N" + num + "Sn" + num + "Ln" + num;
	}
	
	private String getUserKey(int num){
		return "login" + num + "password" + num;
	}
	
	private void addNote(Map<String, Note> map, int num){
		map.put(getNoteKey(num), getNote(num));
	}
	private void addUser(Map<String, User> map, int num){
		map.put(getUserKey(num), getUser(num));
	}
}
