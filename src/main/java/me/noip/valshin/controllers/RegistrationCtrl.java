package me.noip.valshin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import me.noip.valshin.db.Db;
import me.noip.valshin.db.entities.User;
import me.noip.valshin.entities.constants.Templates;
import me.noip.valshin.exceptions.RamDbException;
import me.noip.valshin.tools.data.Validator;

@Controller
@RequestMapping(Templates.REGISTRATION_PATH)
public class RegistrationCtrl {
	@Autowired
	Db db;
	@Autowired
	Validator validator;
	
	@RequestMapping(value = "add" , method = RequestMethod.POST)
	public @ResponseBody String add(@RequestBody User user) {
		if (!validator.checkName(user.getLogin())){
			return "LoginError";
		};
		if (!validator.checkSecondName(user.getPassword())){
			return "PasswordError";
		};
		if (!validator.checkLastName(user.getFio())){
			return "FioError";
		};
		try {
			db.addUser(user);
			return "Sucscess";
		} catch (RamDbException e) {
			return e.getMessage();
		}
	}
}
