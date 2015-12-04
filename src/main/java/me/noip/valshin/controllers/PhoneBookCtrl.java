package me.noip.valshin.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.noip.valshin.db.Db;
import me.noip.valshin.db.entities.Note;
import me.noip.valshin.entities.constants.Sources;
import me.noip.valshin.exceptions.RamDbException;
import me.noip.valshin.tools.data.Validator;
import me.noip.valshin.tools.json.JsonHelper;

@RestController
@RequestMapping(Sources.PHONEBOOK_PATH)
public class PhoneBookCtrl {
	protected Logger logger = Logger.getLogger(PhoneBookCtrl.class.getName());
	@Autowired
	Db db;
	@Autowired
	Validator validator;
	@Autowired
	JsonHelper jsonHelper;
	
	private String checkNote(Note note){
		if (!validator.checkName(note.getName())){
			return jsonHelper.errorAnswer("NameError");
		};
		if (!validator.checkSecondName(note.getSecondName())){
			return jsonHelper.errorAnswer("SecondNameError");
		};
		if (!validator.checkLastName(note.getLastName())){
			return jsonHelper.errorAnswer("LastNameError");
		};
		if (!validator.checkPhone(note.getPhone())){
			return jsonHelper.errorAnswer("PhoneError");
		};
		if (!validator.checkPhone(note.getHomePhone())){
			return jsonHelper.errorAnswer("HomePhoneError");
		};
		if (!validator.checkAdress(note.getAddress())){
			return jsonHelper.errorAnswer("AdressError");
		};
		if (!validator.checkEmail(note.getEmail())){
			return jsonHelper.errorAnswer("EmailError");
		};
		return null;
	};
	
	@RequestMapping(value = "add")
	public String add(@RequestBody Note note) {
		String error = checkNote(note);
		if (error != null){
			return error;
		}
		try {
			db.addNote(note);
			return jsonHelper.okAnswer();
		} catch (RamDbException e) {
			return jsonHelper.errorAnswer(e.getMessage());
		}
	}
	
	@RequestMapping(value = "update")
	public String update(@RequestBody Note note) {
		String error = checkNote(note);
		if (error != null){
			return error;
		}
		try {
			db.updateNote(note);
			return jsonHelper.okAnswer();
		} catch (RamDbException e) {
			return jsonHelper.errorAnswer(e.getMessage());
		}
	}
	
	@RequestMapping(value = "save" , method = RequestMethod.POST, produces = "text/plain; charset=utf-8")
	public String save(@RequestBody Note note) {
		String error = checkNote(note);
		if (error != null){
			return error;
		}
		try {
			db.addNote(note);
			return jsonHelper.okAnswer();
		} catch (RamDbException e1) {
			try {
				db.updateNote(note);
				return jsonHelper.okAnswer();
			} catch (RamDbException e2) {
				return jsonHelper.errorAnswer(e1.getMessage() + "\n" + e2.getMessage());
			}
		}
	}
	
	@RequestMapping(value = "delete")
	public String delete(@RequestBody Note note) {
		String error = checkNote(note);
		if (error != null){
			return jsonHelper.errorAnswer(error);
		}
		try {
			db.deleteNote(note);
			return jsonHelper.okAnswer();
		} catch (RamDbException e) {
			return jsonHelper.errorAnswer(e.getMessage());
		}
	}
	
	@RequestMapping("get_by_name")
	public String getByName(@RequestParam String name) {
		return jsonHelper.jsonAnswer(db.getByName(name));
	}
	
	@RequestMapping("get_by_lastname")
	public String getByLastName(@RequestParam String lastName) {
		return jsonHelper.jsonAnswer(db.getByLastName(lastName));
	}
	
	@RequestMapping("get_by_phone")
	public String getByPhone(@RequestParam String phone) {
		return jsonHelper.jsonAnswer(db.getByPhone(phone));
	}
	
	@RequestMapping("get_all")
	public String getAll() {
		return jsonHelper.jsonAnswer(db.getNotesData());
	}
}
