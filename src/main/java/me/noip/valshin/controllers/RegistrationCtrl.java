package me.noip.valshin.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import me.noip.valshin.db.Db;
import me.noip.valshin.db.entities.User;
import me.noip.valshin.entities.constants.Sources;
import me.noip.valshin.exceptions.RamDbException;
import me.noip.valshin.tools.data.Validator;

@Controller
@RequestMapping(Sources.REGISTER_PATH)
public class RegistrationCtrl {
	private Logger logger = Logger.getLogger(RegistrationCtrl.class.getName());
	@Autowired
	Db db;
	@Autowired
	Validator validator;
	
	@RequestMapping(value = "/add" , method = RequestMethod.POST)
	public @ResponseBody String add(
			@RequestParam String login,
			@RequestParam String fio,
			@RequestParam String password,
			@RequestParam String password_confirm //TODO: match passwords
			) {
		logger.debug("Begin adding user...");
		if (!validator.checkName(login)){
			logger.error("LoginError");
			return "LoginError";
		};
		if (!validator.checkSecondName(password)){
			logger.error("PasswordError");
			return "PasswordError";
		};
		if (!validator.checkLastName(fio)){
			logger.error("FioError");
			return "FioError";
		};
		
		User user = new User();
		user.setLogin(login);
		user.setPassword(password);
		user.setFio(fio);
		try {
			db.addUser(user);
			logger.info("User added successfully");
			return "OK";
		} catch (RamDbException e) {
			logger.error(e.getMessage());
			return e.getMessage();
		}
	}
}
