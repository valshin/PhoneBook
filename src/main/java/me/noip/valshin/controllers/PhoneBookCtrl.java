package me.noip.valshin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	private String checkNote(Note note){
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
		return null;
	};
	
	@RequestMapping(value = "add" , method = RequestMethod.POST)
	public @ResponseBody String add(@RequestBody Note note) {
		String error = checkNote(note);
		if (error != null){
			return error;
		}
		try {
			db.addNote(note);
			return "Sucsess";
		} catch (RamDbException e) {
			return e.getMessage();
		}
	}
	
	@RequestMapping(value = "update" , method = RequestMethod.POST)
	public @ResponseBody String update(@RequestBody Note note) {
		String error = checkNote(note);
		if (error != null){
			return error;
		}
		try {
			db.updateNote(note);
			return "Sucsess";
		} catch (RamDbException e) {
			return e.getMessage();
		}
	}
	
	@RequestMapping(value = "delete" , method = RequestMethod.POST)
	public @ResponseBody String delete(@RequestBody Note note) {
		String error = checkNote(note);
		if (error != null){
			return error;
		}
		try {
			db.deleteNote(note);
			return "Sucscess";
		} catch (RamDbException e) {
			return e.getMessage();
		}
	}
	
	@RequestMapping("get_by_name")
	public @ResponseBody List<Note> getByName(@RequestParam String name) {
		return db.getByName(name);
	}
	
	@RequestMapping("get_by_lastname")
	public @ResponseBody List<Note> getByLastName(@RequestParam String lastName) {
		return db.getByLastName(lastName);
	}
	
	@RequestMapping("get_by_phone")
	public @ResponseBody List<Note> getByPhone(@RequestParam String phone) {
		return db.getByPhone(phone);
	}
	
	@RequestMapping("get_all")
	public @ResponseBody List<Note> getAll() {
		return db.getNotesData();
	}
}
