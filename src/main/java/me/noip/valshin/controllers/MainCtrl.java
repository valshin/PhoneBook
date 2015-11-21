package me.noip.valshin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class MainCtrl {
	@RequestMapping(method = RequestMethod.GET, value="/")
	public String welcome(){
		return "welcome";
	} 
	@RequestMapping("about")
	public String about(){
		return "about";
	}
	@RequestMapping("login")
	public String login(){
		return "login";
	}
	@RequestMapping("phonebook")
	public String phonebook(){
		return "phonebook";
	}
}
