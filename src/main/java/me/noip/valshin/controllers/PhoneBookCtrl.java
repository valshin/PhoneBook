package me.noip.valshin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import me.noip.valshin.db.Db;

@Controller
@RequestMapping("/phonebook")
public class PhoneBookCtrl {
	@Autowired
	Db db;
	
	@RequestMapping(method = RequestMethod.GET, value="/")
	public String welcome(){
		return "phonebook";
	} 
}
