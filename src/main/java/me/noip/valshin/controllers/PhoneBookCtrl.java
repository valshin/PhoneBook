package me.noip.valshin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import me.noip.valshin.db.Db;
import me.noip.valshin.db.entities.Note;
import me.noip.valshin.entities.constants.Templates;
import me.noip.valshin.exceptions.RamDbException;
import me.noip.valshin.tools.data.Validator;

@Controller
@RequestMapping(Templates.PHONEBOOK_PATH)
public class PhoneBookCtrl {
	@Autowired
	Db db;
	@Autowired
	Validator validator;
	
	@RequestMapping(value = "add" , method = RequestMethod.POST)
	public @ResponseBody String add(@RequestBody Note note) {
		if (!validator.checkName(note.getName())){
			return "NameError";
		};
		if (!validator.checkSecondName(note.getSecondName())){
			return "SecondNameError";
		};
		if (!validator.checkLastName(note.getLastName())){
			return "LastNameError";
		};
		if (!validator.checkPhone(note.getPhone())){
			return "PhoneError";
		};
		if (!validator.checkPhone(note.getHomePhone())){
			return "HomePhoneError";
		};
		if (!validator.checkAdress(note.getAdress())){
			return "AdressError";
		};
		if (!validator.checkEmail(note.getEmail())){
			return "EmailError";
		};
		try {
			db.addNote(note);
			return "NoteOK";
		} catch (RamDbException e) {
			return e.getMessage();
		}
	}
}
