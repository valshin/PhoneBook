package me.noip.valshin.db.entities;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import me.noip.valshin.tools.generators.NoteMapGenerator;
import me.noip.valshin.tools.generators.UserMapGenerator;

public class RamStorageTest {
	
	@Test
	public void testGettersAndSetters(){
		MapStorage storage = new MapStorage();
		storage.setNotes(NoteMapGenerator.getRandomNotes(3, 10, 2, 10));
		storage.setUsers(UserMapGenerator.getRandomUsers(3, 10));
		assertTrue(true);
	}
}
