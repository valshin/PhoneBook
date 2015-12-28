package me.noip.valshin.db.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import me.noip.valshin.tools.generators.NoteGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={NoteTest.class})
public class NoteTest {

	@Test
	public void testEquals() {
		Note note;
		Note note2;
		
		note = new Note();
		note.setName("qwerty");
		note.setSecondName("zxc");
		note.setLastName("asdf");
		note.setPhone("1");
		note2 = new Note();
		note2.setName("qwerty");
		note2.setSecondName("zxc");
		note2.setLastName("asdf");
		note2.setPhone("1");
		
		assertTrue(note.equals(note2));
		note.setHomePhone("0");
		assertFalse(note.equals(note2));
		
		note = new Note();
		note.setName("qwerty");
		note.setSecondName("zxc");
		note.setLastName("asdf");
		note.setPhone("1");
		note2 = new Note();
		note2.setName("qwerty");
		note2.setSecondName("zxc");
		note2.setLastName("asdf");
		note2.setPhone("1");
		note2.setOwner(9999L);
		
		assertTrue(note.equals(note2));
		note2.setEmail("");
		assertFalse(note.equals(note2));
		
		note = new Note();
		note.setName("qwerty");
		note.setSecondName("zxc");
		note.setLastName("asdf");
		note.setPhone("1");
		note.setHomePhone("2");
		note.setAddress("poi");
		note.setEmail("q@1.1");
		note2 = new Note();
		note2.setName("qwerty");
		note2.setSecondName("zxc");
		note2.setLastName("asdf");
		note2.setPhone("1");
		note2.setHomePhone("2");
		note2.setAddress("poi");
		note2.setEmail("q@1.1");
		assertTrue(note.equals(note2));
	}
	
	@Test
	public void testClone() {
		for (int i = 1000; i < 100; i++){
			Note note = NoteGenerator.getRandomNote(true);
			Note note2 = note.clone();
			assertTrue(note.equals(note2));
			
			note = NoteGenerator.getRandomNote(false);
			note2 = note.clone();
			assertTrue(note.equals(note2));
		}
	}

}
