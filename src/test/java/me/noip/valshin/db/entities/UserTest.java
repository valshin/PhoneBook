package me.noip.valshin.db.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import me.noip.valshin.tools.generators.UserGenerator;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={UserTest.class})
public class UserTest {

	@Test
	public void testEquals() {
		User user;
		User user2;
		
		user = new User();
		user.setLogin("qwerty");
		user.setPassword("pass");
		user.setFio("asdf");
		user2 = new User();
		user2.setLogin("qwerty");
		user2.setPassword("pass");
		user2.setFio("asdf");
		assertTrue(user.isEqual(user2));
		user2.setFio("asdfq");
		assertFalse(user.isEqual(user2));
		
		user = new User();
		user.setLogin("qwerty");
		user.setPassword("pass");
		user.setFio("asdf");
		user2 = new User();
		user2.setLogin("qwerty");
		user2.setPassword("pass");
		user2.setFio("asdf");
		user2.setId(333L);
		assertTrue(user.isEqual(user2));
		user.setId(111L);
		assertTrue(user.isEqual(user2));
	}
	
	@Test
	public void testClone() {
		for (int i = 1000; i < 100; i++){
			User user = UserGenerator.getRandomUser();
			User user2 = user.clone();
			assertTrue(user.equals(user2));
		}
	}
}
