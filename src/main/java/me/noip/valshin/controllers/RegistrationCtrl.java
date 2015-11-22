package me.noip.valshin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import me.noip.valshin.db.Db;
import me.noip.valshin.entities.constants.Templates;

@Controller
@RequestMapping(Templates.REGISTRATION_PATH)
public class RegistrationCtrl {
	@Autowired
	Db db;
	
	@RequestMapping("do")
	@ResponseBody
	public String add(
			@RequestParam String login, 
			@RequestParam String pass,
			@RequestParam String fio){
		return "AD_____________________________AD";
	}
}
