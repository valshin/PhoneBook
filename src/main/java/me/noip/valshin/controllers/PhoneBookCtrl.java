package me.noip.valshin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import me.noip.valshin.db.Db;

@Controller
@RequestMapping("/phonebook")
public class PhoneBookCtrl {
	@Autowired
	Db db;
	
	@RequestMapping("add")
	@ResponseBody
	
	public String add(
			@RequestParam String firstName, 
			@RequestParam String secondName,
			@RequestParam String lastName,
			@RequestParam String phone,
			@RequestParam String homeName,
			@RequestParam String adress,
			@RequestParam String email){
		return "AD_____________________________AD";
	}
}
