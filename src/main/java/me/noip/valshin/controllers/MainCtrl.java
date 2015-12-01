package me.noip.valshin.controllers;

import java.security.Principal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import me.noip.valshin.db.Db;
import me.noip.valshin.db.entities.User;
import me.noip.valshin.exceptions.RamDbException;
import me.noip.valshin.tools.data.Validator;

@SpringBootApplication
@RestController
public class MainCtrl {
	
	private Logger logger = Logger.getLogger(MainCtrl.class.getName());
	@Autowired
	Db db;
	@Autowired
	Validator validator;
	
	@RequestMapping(value = "/adduser" , method = RequestMethod.POST, produces = "text/plain; charset=utf-8")
	public @ResponseBody String add(
			@RequestParam String login,
			@RequestParam String fio,
			@RequestParam String password
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
	
	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}
}
