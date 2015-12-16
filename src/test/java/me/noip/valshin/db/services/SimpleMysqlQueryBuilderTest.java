package me.noip.valshin.db.services;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import me.noip.valshin.db.MysqlQueryBuilder;
import me.noip.valshin.db.entities.Note;
import me.noip.valshin.db.entities.User;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = { SimpleMysqlQueryBuilderTest.class, SimpleMysqlQueryBuilder.class })
@Configuration
public class SimpleMysqlQueryBuilderTest {
	@Autowired
	MysqlQueryBuilder queryBuilder;
	
	@Test
	public void userReadTest(){
		assertEquals("SELECT `id`, `fio` from `users` WHERE `login` = login1 AND `password` = pass1", queryBuilder.userRead("login1", "pass1"));
	}
	
	@Test
	public void userWriteTest(){
		String expected = "INSERT INTO `users` SET `login` = 'login1', `password` = 'pass1', `fio` = 'fio1'";
		String obtained = queryBuilder.userWrite(new User(){{
			setLogin("login1");
			setPassword("pass1");
			setFio("fio1");
		}});
		assertEquals(expected, obtained);
	}
	
	@Test
	public void readNotesTest1(){
		String expected = "SELECT `id`, `name`, `second_name`, `last_name`, `phone`, `home_phone`, `address`, `email`, `owner` from `notes` WHERE `owner` = '123'";
		String obtained = queryBuilder.notesRead(123L);
		assertEquals(expected, obtained);
	}
	
	@Test
	public void readNotesTest2(){
		String expected = "SELECT `id`, `name`, `second_name`, `last_name`, `phone`, `home_phone`, `address`, `email`, `owner` from `notes` WHERE `owner` = '123' AND (`home_phone` LIKE '%1111111%' OR `phone` LIKE '%0000000%')";
		String obtained = queryBuilder.notesRead(
				123L, 
				new HashMap<String, String>(){{
					put("phone", "0000000");
					put("home_phone", "1111111");
				}}
			);
		assertEquals(expected, obtained);
	}
	
	@Test
	public void readNotesTest3(){
		String expected = "SELECT `id`, `name`, `second_name`, `last_name`, `phone`, `home_phone`, `address`, `email`, `owner` from `notes` WHERE `owner` = '123' AND (`home_phone` LIKE '%1111111%' OR `phone` LIKE '%0000000%' OR `name` LIKE '%name1%' OR `last_name` LIKE '%lastname1%')";
		String obtained = queryBuilder.notesRead(
				123L, 
				new HashMap<String, String>(){{
					put("phone", "0000000");
					put("home_phone", "1111111");
					put("last_name", "lastname1");
					put("name", "name1");
				}}
			);
		assertEquals(expected, obtained);
	}
	
	@Test
	public void readNotesTest4(){
		String expected = "SELECT `id`, `name`, `second_name`, `last_name`, `phone`, `home_phone`, `address`, `email`, `owner` from `notes` WHERE `owner` = '123' AND (`home_phone` = '%1111111%' AND `phone` = '%0000000%' AND `name` = '%name1%' AND `last_name` = '%lastname1%')";
		String obtained = queryBuilder.notesRead(
				123L, 
				new HashMap<String, String>(){{
					put("phone", "0000000");
					put("home_phone", "1111111");
					put("last_name", "lastname1");
					put("name", "name1");
				}},
				"=",
				"AND"
			);
		assertEquals(expected, obtained);
	}
	
	@Test
	public void readNotesTest5(){
		String expected = "SELECT `id`, `name`, `second_name`, `last_name`, `phone`, `home_phone`, `address`, `email`, `owner` from `notes` WHERE `owner` = '123' AND (`name` LIKE '%name1%')";
		String obtained = queryBuilder.notesRead(
				123L, 
				new HashMap<String, String>(){{
					put("name", "name1");
				}}
			);
		assertEquals(expected, obtained);
	}
	
	@Test
	public void readNotesTest6(){
		String expected = "SELECT `id`, `name`, `second_name`, `last_name`, `phone`, `home_phone`, `address`, `email`, `owner` from `notes` WHERE `owner` = '123' AND (`last_name` LIKE '%lastname1%')";
		String obtained = queryBuilder.notesRead(
				123L, 
				new HashMap<String, String>(){{
					put("last_name", "lastname1");
				}}
			);
		assertEquals(expected, obtained);
	}
	
	@Test
	public void updateNotesTest1(){
		String expected = "UPDATE `notes` WHERE `id` = '1' SET `name` = 'name1', `second_name` = 'sn1', `last_name` = 'ln1', `phone` = '123'";
		String obtained = queryBuilder.noteUpdate(1L, new Note(){{
			setName("name1");
			setSecondName("sn1");
			setLastName("ln1");
			setPhone("123");
		}});
		assertEquals(expected, obtained);
	}
	
	@Test
	public void updateNotesTest2(){
		String expected = "UPDATE `notes` WHERE `id` = '2' SET `name` = 'name1', `second_name` = 'sn1', `last_name` = 'ln1', `phone` = '123', `home_phone` = '321', `address` = 'qwerty', `email` = 'qwe@rt.yu'";
		String obtained = queryBuilder.noteUpdate(2L, new Note(){{
			setName("name1");
			setSecondName("sn1");
			setLastName("ln1");
			setPhone("123");
			setHomePhone("321");
			setAddress("qwerty");
			setEmail("qwe@rt.yu");
		}});
		assertEquals(expected, obtained);
	}
	
	@Test
	public void updateNotesTest3(){
		String expected = "UPDATE `notes` WHERE `id` = '333' SET `name` = 'name1', `second_name` = 'sn1', `last_name` = 'ln1', `phone` = '123', `address` = 'qwerty'";
		String obtained = queryBuilder.noteUpdate(333L, new Note(){{
			setName("name1");
			setSecondName("sn1");
			setLastName("ln1");
			setPhone("123");
			setAddress("qwerty");
		}});
		assertEquals(expected, obtained);
	}
	
	@Test
	public void updateNotesTest4(){
		String expected = "UPDATE `notes` WHERE `id` = '444' SET `name` = 'name1', `second_name` = 'sn1', `last_name` = 'ln1', `phone` = '123', `email` = 'qwe@rt.yu'";
		String obtained = queryBuilder.noteUpdate(444L, new Note(){{
			setName("name1");
			setSecondName("sn1");
			setLastName("ln1");
			setPhone("123");
			setEmail("qwe@rt.yu");
		}});
		assertEquals(expected, obtained);
	}
	
	@Test
	public void deleteNotesTest(){
		String expected = "DELETE FROM `notes` WHERE `id` = '123456789'";
		String obtained = queryBuilder.noteDelete(123456789L);
		assertEquals(expected, obtained);
	}
	
	@Test
	public void writeNoteTest1(){
		String expected = "INSERT INTO `notes` SET `owner` = '1', `name` = 'name1', `second_name` = 'sn1', `last_name` = 'ln1', `phone` = '123'";
		String obtained = queryBuilder.noteWrite(1L, new Note(){{
			setName("name1");
			setSecondName("sn1");
			setLastName("ln1");
			setPhone("123");
		}});
		assertEquals(expected, obtained);
	}
	
	@Test
	public void writeNoteTest2(){
		String expected = "INSERT INTO `notes` SET `owner` = '2', `name` = 'name1', `second_name` = 'sn1', `last_name` = 'ln1', `phone` = '123', `home_phone` = '321', `address` = 'qwerty', `email` = 'qwe@rt.yu'";
		String obtained = queryBuilder.noteWrite(2L, new Note(){{
			setName("name1");
			setSecondName("sn1");
			setLastName("ln1");
			setPhone("123");
			setHomePhone("321");
			setAddress("qwerty");
			setEmail("qwe@rt.yu");
		}});
		assertEquals(expected, obtained);
	}
	
	@Test
	public void writeNoteTest3(){
		String expected = "INSERT INTO `notes` SET `owner` = '333', `name` = 'name1', `second_name` = 'sn1', `last_name` = 'ln1', `phone` = '123', `address` = 'qwerty'";
		String obtained = queryBuilder.noteWrite(333L, new Note(){{
			setName("name1");
			setSecondName("sn1");
			setLastName("ln1");
			setPhone("123");
			setAddress("qwerty");
		}});
		assertEquals(expected, obtained);
	}
	
	@Test
	public void writeNoteTest4(){
		String expected = "INSERT INTO `notes` SET `owner` = '444', `name` = 'name1', `second_name` = 'sn1', `last_name` = 'ln1', `phone` = '123', `email` = 'qwe@rt.yu'";
		String obtained = queryBuilder.noteWrite(444L, new Note(){{
			setName("name1");
			setSecondName("sn1");
			setLastName("ln1");
			setPhone("123");
			setEmail("qwe@rt.yu");
		}});
		assertEquals(expected, obtained);
	}
}
