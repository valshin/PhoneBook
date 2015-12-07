package me.noip.valshin.db.entities;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import me.noip.valshin.tools.generators.NotesMapGenerator;
import me.noip.valshin.tools.generators.UsersMapGenerator;

public class RamStorageTest {
	
	@Test
	public void testGettersAndSetters(){
		MapStorage storage = new MapStorage();
		storage.setNotes(NotesMapGenerator.getRandomNotes(3, 10, 2, 10));
		storage.setUsers(UsersMapGenerator.getRandomUsers(3, 10));
		assertTrue(true);
	}
}
