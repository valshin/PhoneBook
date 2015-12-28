package me.noip.valshin.tools.data.services;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import me.noip.valshin.tools.data.Validator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DataValidatorTest.class, DataValidator.class })
public class DataValidatorTest {
	@Autowired
	Validator validator;

	// Users
	@Test
	public void checkLoginTest() {
		assertTrue(validator.checkLogin("qwe"));
		assertTrue(validator.checkLogin("QwE"));
		assertFalse(validator.checkLogin("q.e"));
		assertFalse(validator.checkLogin("qe"));
		assertFalse(validator.checkLogin("йцу"));
		assertFalse(validator.checkLogin(""));
	};
	@Test
	public void checkPasswordTest() {
		assertTrue(validator.checkPassword("Pass1"));
		assertTrue(validator.checkPassword("paSS12"));
		assertTrue(validator.checkPassword("passW0rd"));
		assertTrue(validator.checkPassword("p@a$$W0rd*!:;',.?/{}[]|\\^&()`~"));
		assertFalse(validator.checkPassword("passw"));
		assertFalse(validator.checkPassword("passW"));
		assertFalse(validator.checkPassword("passw1"));
		assertFalse(validator.checkPassword("paS1"));
		assertFalse(validator.checkPassword("pass1"));
		assertFalse(validator.checkPassword(""));
	};
	@Test
	public void checkFioTest() {
		assertTrue(validator.checkFio("12345"));
		assertTrue(validator.checkFio("';l';l'lk;"));
		assertFalse(validator.checkFio("qq"));
		assertFalse(validator.checkFio(""));
	};

	// Notes
	@Test
	public void checkNameTest() {
		assertTrue(validator.checkName("1234"));
		assertTrue(validator.checkName("';l';l'lk;"));
		assertTrue(validator.checkName("qwerty"));
		assertFalse(validator.checkName("qq3"));
		assertFalse(validator.checkName(""));
	};
	@Test
	public void checkSecondNameTest() {
		assertTrue(validator.checkSecondName("1234"));
		assertTrue(validator.checkSecondName("';l';l'lk;"));
		assertTrue(validator.checkSecondName("qwerty"));
		assertFalse(validator.checkSecondName("qq3"));
		assertFalse(validator.checkSecondName(""));
	};
	@Test
	public void checkLastNameTest() {
		assertTrue(validator.checkLastName("1234"));
		assertTrue(validator.checkLastName("';l';l'lk;"));
		assertTrue(validator.checkLastName("qwerty"));
		assertFalse(validator.checkLastName("qq3"));
		assertFalse(validator.checkLastName(""));
	};
	@Test
	public void checkPhoneTest() {
		assertTrue(validator.checkPhone("+380(66)1234567"));
		assertTrue(validator.checkPhone("+380(55)1234567"));
		assertTrue(validator.checkPhone("+380(00)0000000"));
		assertFalse(validator.checkPhone("380(00)0000000"));
		assertFalse(validator.checkPhone("+380(00)123456"));
		assertFalse(validator.checkPhone(""));
	};
	@Test
	public void checkHomePhoneTest() {
		assertTrue(validator.checkHomePhone(""));
		assertTrue(validator.checkHomePhone("+380(66)1234567"));
		assertTrue(validator.checkHomePhone("+380(55)1234567"));
		assertTrue(validator.checkHomePhone("+380(00)0000000"));
		assertFalse(validator.checkHomePhone("380(00)0000000"));
		assertFalse(validator.checkHomePhone("+380(00)123456"));
	};
	@Test
	public void checkAdressTest() {

	};
	@Test
	public void checkEmailTest() {
		assertTrue(validator.checkEmail(""));
		assertTrue(validator.checkEmail("qwe@rty.ru"));
		assertTrue(validator.checkEmail("webmaster@muller.de"));
		assertFalse(validator.checkEmail("user@.invalid.com"));
		assertFalse(validator.checkEmail("@.invalid.com"));
	};
}
